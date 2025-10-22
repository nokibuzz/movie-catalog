import React, { useState, useEffect } from "react";
import { Container, Row } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { green } from "@material-ui/core/colors";
import AddIcon from "@material-ui/icons/Add";
import Fab from "@material-ui/core/Fab";
import Tooltip from "@material-ui/core/Tooltip";
import AdminMovieTable from "./adminMovieTable";

import AddMovie from "./addMovie";
import adminApi from "../../api/admin-movie-api";

const useStyles = makeStyles((theme) => ({
  absolute: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3),
  },
}));

const AdminMovies = () => {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [fetchMovies, setFetchMovies] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSave = (movie) => {
    console.log("Movie to save", movie);
    adminApi
      .addMovie(movie)
      .then(() => {
        setFetchMovies(!fetchMovies);
        handleClose();
      })
      .catch(console.log("Nooo"));
  };

  return (
    <div>
      <h1>Movies</h1>
      <Tooltip title="Add movie" aria-label="add">
        <Fab
          style={{ background: green[500], color: "white", outline: "none" }}
          className={classes.absolute}
          onClick={handleOpen}
        >
          <AddIcon />
        </Fab>
      </Tooltip>
      <Container>
        <Row>
          <AdminMovieTable fetchMovies={fetchMovies} />
        </Row>
      </Container>
      <AddMovie open={open} handleClose={handleClose} handleSave={handleSave} />
    </div>
  );
};

export default AdminMovies;
