import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Autocomplete from "@material-ui/lab/Autocomplete";

import adminApi from "../../api/admin-actor-api";

const useStyles = makeStyles((theme) => ({
  modal: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #456",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  formControl: {
    margin: theme.spacing(0.5),
    width: "250px",
  },
}));

const UpdateMovie = (props) => {
  const classes = useStyles();
  const { open, movie, handleClose, handleUpdate } = props;
  const [title, setTitle] = useState(null);
  const [description, setDescription] = useState(null);
  const [year, setYear] = useState(null);
  const [duration, setDuration] = useState(null);
  const [selectActors, setSelectActors] = useState([]);
  const [actors, setActors] = useState([]);

  useEffect(() => {
    console.log("Update", movie);
    adminApi
      .getActors()
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        setSelectActors(json.content);
      })
      .catch((err) => {
        console.log(err);
      });
    if (movie != null) {
      setTitle(movie.title);
      setDescription(movie.description);
      setYear(movie.year);
      setDuration(movie.duration);
    }
  }, [open, movie]);

  const handleChange = (event) => {
    switch (event.target.name) {
      case "Title":
        setTitle(event.target.value);
        break;
      case "Description":
        setDescription(event.target.value);
        break;
      case "Year":
        setYear(event.target.value);
        break;
      case "Duration":
        setDuration(event.target.value);
        break;
      default:
        break;
    }
  };

  const callUpdate = () => {
    handleUpdate({
      title: title,
      description: description,
      year: year,
      duration: duration,
      actors: actors.map((a) => a.id),
    });
  };

  return (
    <Modal
      aria-labelledby="transition-modal-title"
      aria-describedby="transition-modal-description"
      className={classes.modal}
      open={open}
      onClose={handleClose}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500,
      }}
    >
      <Fade in={open}>
        <div className={classes.paper}>
          <h2 id="transition-modal-title" align="center">
            Update movie
          </h2>
          <form
            className={classes.modal}
            noValidate
            autoComplete="off"
            onSubmit={callUpdate}
          >
            <TextField
              id="title"
              label="Title"
              name="Title"
              variant="outlined"
              placeholder="Movie title"
              className={classes.formControl}
              value={title}
              onChange={handleChange}
            />

            <TextField
              id="description"
              label="Description"
              name="Description"
              placeholder="Description"
              multiline
              variant="outlined"
              className={classes.formControl}
              value={description}
              onChange={handleChange}
            />

            <TextField
              id="year"
              label="Year"
              name="Year"
              type="number"
              inputProps={{ min: "1920", max: "2030", step: "1" }}
              variant="outlined"
              placeholder="Year"
              className={classes.formControl}
              value={year}
              onChange={handleChange}
            />

            <TextField
              id="duration"
              label="Duration"
              name="Duration"
              type="number"
              inputProps={{ min: "1920", max: "2030", step: "1" }}
              variant="outlined"
              placeholder="Duration"
              className={classes.formControl}
              value={duration}
              onChange={handleChange}
            />

            <Autocomplete
              multiple
              id="tags-outlined"
              className={classes.formControl}
              options={selectActors}
              getOptionLabel={(option) => option.name}
              filterSelectedOptions
              onChange={(event, values) => setActors(values)}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  label="Actors"
                  fullWidth
                />
              )}
            />

            <br />
          </form>

          <Button
            variant="contained"
            color="secondary"
            onClick={handleClose}
            style={{
              margin: 2,
              marginLeft: 50,
              marginTop: 20,
              outline: "none",
            }}
          >
            Close
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={callUpdate}
            style={{
              margin: 2,
              marginLeft: 10,
              marginTop: 20,
              outline: "none",
            }}
          >
            Update
          </Button>
        </div>
      </Fade>
    </Modal>
  );
};

export default UpdateMovie;
