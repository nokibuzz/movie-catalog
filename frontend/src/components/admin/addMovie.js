import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Rating from "@material-ui/lab/Rating";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from "@material-ui/core/CardMedia";
import Typography from "@material-ui/core/Typography";
import { ContentSort } from "material-ui/svg-icons";

import imageApi from "../../api/image-api";

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
    maxHeight: "100%",
    overflowY: "scroll",
  },
  formControl: {
    margin: theme.spacing(0.5),
    width: "250px",
  },
  label: {
    fontFamily: "sans-serif",
  },
  root: {
    padding: theme.spacing(3),
    marginLeft: "32%",
  },
  input: {
    display: "none",
  },
  image: {
    position: "relative",
    height: 300,
    width: 250,
    boxShadow: `0 16px 24px 2px rgba(0, 0, 0, 0.14), 0 6px 30px 5px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2)`,
    [theme.breakpoints.down("xs")]: {
      width: "100% !important", // Overrides inline-style
      height: 100,
    },
    "&:hover, &$focusVisible": {
      zIndex: 1,
      "& $imageBackdrop": {
        opacity: 0.15,
      },
      "& $imageMarked": {
        opacity: 0,
      },
      "& $imageMarkedPrice": {
        backgroundColor: "black",
      },
      "& $imageTitle": {
        border: "1px solid currentColor",
        backgroundColor: "#252525c2",
      },
    },
    margin: "20px 2%",
  },
  imageBackdrop: {
    position: "absolute",
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    backgroundColor: theme.palette.common.black,
    opacity: 0.4,
    transition: theme.transitions.create("opacity"),
  },
  imageTitle: {
    position: "relative",
    padding: `${theme.spacing(2)}px ${theme.spacing(4)}px ${
      theme.spacing(1) + 6
    }px`,
    transition: theme.transitions.create("background"),
  },
  imageMarked: {
    height: 3,
    width: 18,
    backgroundColor: theme.palette.common.white,
    position: "absolute",
    bottom: -2,
    left: "calc(50% - 9px)",
    transition: theme.transitions.create("opacity"),
  },
  imageMarkedPrice: {
    padding: "5px",
    borderRadius: "5px",
    transition: theme.transitions.create("background"),
  },
  imageChangeButton: {
    marginTop: 20,
    marginLeft: 30,
  },
  imageName: {
    marginTop: 20,
  },
  finalText: {
    marginTop: 20,
    textAlign: "center",
  },
}));

const AddMovie = (props) => {
  const classes = useStyles();
  const { open, handleClose, handleSave } = props;
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [ranking, setRanking] = useState("");
  const [year, setYear] = useState("");
  const [duration, setDuration] = useState("");
  const [genres, setGenres] = useState([]);
  const [trailer, setTrailer] = useState("");
  const [imageId, setImageId] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);
  const [imageData, setImageData] = useState(null);

  const handleChange = (event) => {
    switch (event.target.name) {
      case "Title":
        setTitle(event.target.value);
        break;
      case "Description":
        setDescription(event.target.value);
        break;
      case "Ranking":
        setRanking(event.target.value);
        break;
      case "Year":
        setYear(event.target.value);
        break;
      case "Duration":
        setDuration(event.target.value);
        break;
      case "Genre":
        setGenres(event.target.value);
        break;
      case "TrailerURL":
        setTrailer(event.target.value.split("v=").pop());
        break;
      default:
        break;
    }
  };

  const callSave = () => {
    handleSave({
      title: title,
      description: description,
      ranking: ranking,
      year: year,
      duration: duration,
      genres: genres,
      trailerUrl: trailer,
      imageID: imageId,
    });
  };

  const handleUploadClick = (event) => {
    const file = event.target.files[0];
    const imageData = new FormData();
    imageData.append("image", file);
    setImageData(imageData);
    setImagePreview(URL.createObjectURL(file));
  };

  const uploadImage = () => {
    imageApi
      .uploadImage(imageData)
      .then((res) => {
        setImageId(res.data.id);
      })
      .catch(console.log("Nooo"));
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
            Create movie
          </h2>
          <form
            className={classes.modal}
            noValidate
            autoComplete="off"
            onSubmit={callSave}
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
            <label for="ranking" className={classes.label}>
              Ranking
            </label>
            <Rating
              id="ranking"
              label="Ranking"
              name="Ranking"
              defaultValue={2.5}
              precision={0.5}
              value={ranking}
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
              inputProps={{ min: "1", max: "300", step: "1" }}
              variant="outlined"
              placeholder="Duration"
              className={classes.formControl}
              value={duration}
              onChange={handleChange}
            />
            <FormControl
              variant="outlined"
              required
              className={classes.formControl}
            >
              <InputLabel id="demo-simple-select-outlined-label">
                Genre
              </InputLabel>
              <Select
                id="genre"
                label="Genre"
                name="Genre"
                labelId="genre"
                multiple
                value={genres}
                onChange={handleChange}
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
            <TextField
              id="filled-basic"
              label="Trailer URL"
              name="TrailerURL"
              variant="standard"
              placeholder="Trailer URL"
              className={classes.formControl}
              value={trailer}
              onChange={handleChange}
            />
            <Container maxWidth="lg" className={classes.root}>
              <Grid container spacing={2}>
                <Grid item xs={4}>
                  <Card>
                    <CardActionArea>
                      <CardMedia
                        component="img"
                        image={
                          imagePreview !== null
                            ? imagePreview
                            : "https://www.amerikickkansas.com/wp-content/uploads/2017/04/default-image.jpg"
                        }
                      />
                    </CardActionArea>
                  </Card>
                  <input
                    accept="image/*"
                    className={classes.input}
                    id="upload-profile-image"
                    type="file"
                    onChange={handleUploadClick}
                  />
                  <label htmlFor="upload-profile-image">
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.imageChangeButton}
                      component="span"
                    >
                      Change Image
                    </Button>
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.imageChangeButton}
                      onClick={() => uploadImage()}
                    >
                      Upload Image
                    </Button>
                  </label>
                </Grid>
              </Grid>
            </Container>
            <br />
          </form>

          <Button
            variant="contained"
            color="secondary"
            onClick={handleClose}
            style={{
              margin: 2,
              marginLeft: 300,
              marginTop: 20,
              outline: "none",
            }}
          >
            Close
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={callSave}
            style={{
              margin: 2,
              marginLeft: 10,
              marginTop: 20,
              outline: "none",
            }}
          >
            Save
          </Button>
        </div>
      </Fade>
    </Modal>
  );
};

export default AddMovie;
