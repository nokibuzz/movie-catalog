import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import Paper from "@material-ui/core/Paper";
import AdminCollapseRow from "../common/admin-collapse-row";
import LinearProgress from "@material-ui/core/LinearProgress";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";

import {
  StyledTableRow,
  StyledTableCell,
} from "../../components/common/styled-table";

import adminApi from "../../api/admin-movie-api";
import DraggableDialog from "../common/draggableDialog";
import UpdateMovie from "../admin/updateMovie";

const columns = [
  { id: "title", label: "Title", minWidth: 170 },
  {
    id: "genres",
    label: "Genres",
    minWidth: 100,
    align: "center",
    isList: true,
  },
  { id: "description", label: "Description", minWidth: 170 },
  {
    id: "duration",
    label: "Duration",
    minWidth: 100,
    format: (time) =>
      Math.floor(time / 60) +
      ":" +
      (time % 60 > 9 ? time % 60 : "0" + (time % 60)),
  },
  // {
  //   id: "size",
  //   label: "Size\u00a0(km\u00b2)",
  //   minWidth: 170,
  //   align: "right",
  //   format: (value) => value.toLocaleString("en-US"),
  // },
];

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
  },
  container: {
    maxHeight: 650,
  },
  progress: {
    width: "100%",
    "& > * + *": {
      marginTop: theme.spacing(2),
    },
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
}));

const AdminMovieTable = (props) => {
  const { fetchMovies } = props;

  const classes = useStyles();

  const [page, setPage] = useState(0);
  const [numberOfElements, setNumberOfElements] = useState(10);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [movies, setMovies] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [title, setTitle] = useState(null);
  const [genre, setGenre] = useState(null);

  const [openUpdateDialog, setOpenUpdateDialog] = useState(false);
  const [updateMovie, setUpdateMovie] = useState(null);

  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
  const [deleteMovie, setDeleteMovie] = useState(null);
  const [deletedMovieMessage, setDeletedMovieMessage] = useState("");

  const [trigger, setTrigger] = useState(false);

  useEffect(() => {
    adminApi
      .getMovies(title, genre, page, rowsPerPage)
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        setMovies(json.content);
        setNumberOfElements(json.totalElements);
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [title, genre, page, rowsPerPage, fetchMovies, trigger]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const handleSelectGenre = (event) => {
    setGenre(event.target.value);
  };

  // update movie
  const handleClickOpenUpdateDialog = (movie) => {
    console.log("Update movie", movie);
    setUpdateMovie(movie);
    setOpenUpdateDialog(true);
  };

  const handleCloseUpdateDialog = () => {
    setOpenUpdateDialog(false);
  };

  const handleUpdate = (movie) => {
    adminApi
      .updateMovie(updateMovie.id, movie)
      .then(() => {
        setTrigger(!trigger);
        handleCloseUpdateDialog();
      })
      .catch(console.log("Nooo"));
  };

  // delete movie
  const handleClickOpenDeleteDialog = (movie) => {
    setDeleteMovie(movie);
    setDeletedMovieMessage(
      "Are you sure want to delete movie " + movie.title + "?"
    );
    setOpenDeleteDialog(true);
  };

  const handleCloseDeleteDialog = () => {
    setOpenDeleteDialog(false);
  };

  const handleDelete = () => {
    console.log("Movie to delete", deleteMovie);
    adminApi
      .deleteMovie(deleteMovie.id)
      .then(() => {
        setTrigger(!trigger);
        handleCloseDeleteDialog();
      })
      .catch(console.log("Nooo"));
  };

  const handleClickGenre = (item) => {
    setGenre(item);
  };

  const handleChangeTitle = (event) => {
    const titleSearch = event.target.value;
    if (titleSearch && (titleSearch.length > 2 || genre)) {
      setTitle(titleSearch);
    } else if (!titleSearch) {
      setTitle(null);
    }
  };

  if (!isLoaded)
    return (
      <div className={classes.progress}>
        <p>Loading...</p>
        <LinearProgress />
      </div>
    );

  return (
    <Paper className={classes.root}>
      <DraggableDialog
        open={openDeleteDialog}
        title="Delete movie"
        content={deletedMovieMessage}
        handleClose={handleCloseDeleteDialog}
        handleDelete={handleDelete}
      />
      <UpdateMovie
        open={openUpdateDialog}
        movie={updateMovie}
        handleClose={handleCloseUpdateDialog}
        handleUpdate={handleUpdate}
      />
      <TextField
        id="outlined-basic"
        placeholder="Movie title"
        label="Movie title"
        className="mb-3"
        onChange={handleChangeTitle}
        style={{
          margin: "0 auto",
          marginLeft: "30%",
          maxWidth: 200,
        }}
        variant="outlined"
      />
      <FormControl
        variant="outlined"
        className={classes.formControl}
        style={{
          margin: "0 auto",
          marginLeft: "5%",
          maxWidth: 200,
        }}
      >
        <InputLabel id="demo-simple-select-outlined-label">Genre</InputLabel>
        <Select
          labelId="demo-simple-select-outlined-label"
          id="demo-simple-select-outlined"
          value={genre}
          onChange={handleSelectGenre}
          label="Genre"
        >
          <MenuItem value="">
            <em></em>
          </MenuItem>
          <MenuItem value="ACTION">ACTION</MenuItem>
          <MenuItem value="COMEDY">COMEDY</MenuItem>
          <MenuItem value="ROMANCE">ROMANCE</MenuItem>
          <MenuItem value="HORROR">HORROR</MenuItem>
          <MenuItem value="CRIME">CRIME</MenuItem>
          <MenuItem value="SCIENCEFICTION">SCIENCEFICTION</MenuItem>
          <MenuItem value="FAMILLY">FAMILLY</MenuItem>
          <MenuItem value="DRAMA">DRAMA</MenuItem>
          <MenuItem value="THRILLER">THRILLER</MenuItem>
        </Select>
      </FormControl>
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <StyledTableRow>
              <StyledTableCell></StyledTableCell>
              {columns.map((column) => (
                <StyledTableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </StyledTableCell>
              ))}
              <StyledTableCell align="center">Rating</StyledTableCell>
              <StyledTableCell></StyledTableCell>
              <StyledTableCell></StyledTableCell>
            </StyledTableRow>
          </TableHead>
          <TableBody>
            {movies.map((row) => {
              return (
                <AdminCollapseRow
                  key={row.id}
                  row={row}
                  columns={columns}
                  handleUpdate={handleClickOpenUpdateDialog}
                  handleDelete={handleClickOpenDeleteDialog}
                  handleClickGenre={handleClickGenre}
                />
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[1, 2, 5, 10, 20, 50]}
        component="div"
        count={numberOfElements}
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default AdminMovieTable;
