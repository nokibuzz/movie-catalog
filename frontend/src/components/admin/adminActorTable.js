import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import Paper from "@material-ui/core/Paper";
import LinearProgress from "@material-ui/core/LinearProgress";
import TextField from "@material-ui/core/TextField";
import Moment from "moment";

import {
  StyledTableRow,
  StyledTableCell,
} from "../../components/common/styled-table";

import adminApi from "../../api/admin-actor-api";

const columns = [
  { id: "name", label: "Name", minWidth: 170 },
  {
    id: "birthday",
    label: "Birthday",
    minWidth: 100,
    isDate: true,
    format: (value) => Moment(value).format("DD/MM/YYYY"),
  },
  { id: "roles", label: "Roles", minWidth: 200 },
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

const AdminActorTable = (props) => {
  const { fetchActors } = props;

  const classes = useStyles();

  const [page, setPage] = useState(0);
  const [numberOfElements, setNumberOfElements] = useState(10);
  const [rowsPerPage, setRowsPerPage] = useState(2);
  const [actors, setActors] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [name, setName] = useState(null);

  useEffect(() => {
    adminApi
      .getActors(name, page, rowsPerPage)
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        setActors(json.content);
        setNumberOfElements(json.totalElements);
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [name, page, rowsPerPage, fetchActors]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const handleChangeName = (event) => {
    const nameSearch = event.target.value;
    if (nameSearch && nameSearch.length > 2) {
      setName(nameSearch);
    } else if (!nameSearch) {
      setName(null);
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
      <TextField
        id="outlined-basic"
        placeholder="Actor name"
        label="Actor name"
        className="mb-3"
        onChange={handleChangeName}
        style={{
          margin: "0 10%",
          maxWidth: 300,
        }}
        variant="outlined"
      />
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <StyledTableRow>
              {columns.map((column) => (
                <StyledTableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </StyledTableCell>
              ))}
            </StyledTableRow>
          </TableHead>
          <TableBody>
            {actors.map((row) => {
              return (
                <StyledTableRow
                  hover
                  role="checkbox"
                  tabIndex={-1}
                  key={row.id}
                >
                  {columns.map((column) => {
                    const value = row[column.id];
                    return (
                      <StyledTableCell key={column.id} align={column.align}>
                        {column.format && column.isDate
                          ? column.format(value)
                          : value}
                      </StyledTableCell>
                    );
                  })}
                </StyledTableRow>
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

export default AdminActorTable;
