import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Container, Row, Col } from "react-bootstrap";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import UserMovieList from "./userMovieList";
import UserMovieDetails from "./userMovieDetails";
import getScrollPosition from "../../helper/infinite-scroll";
import * as movieHelper from "../../helper/movies-helper";

import userApi from "../../api/user-movie-api";
import adminApi from "../../api/admin-movie-api";

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
}));

const UserMoviePage = () => {
  const [movies, setMovies] = useState([]);
  const [actors, setActors] = useState([]);
  const [titleSearch, setTitleSearch] = useState();
  const [genreSearch, setGenreSearch] = useState();
  const [title, setTitle] = useState();
  const [description, setDescription] = useState();
  const [duration, setDuration] = useState();
  const [ranking, setRanking] = useState();
  const [year, setYear] = useState();
  const [genres, setGenres] = useState();
  const [trailer, setTrailer] = useState();
  const [isLoaded, setIsLoaded] = useState(false);
  const [isLoadingMovie, setIsLoadingMovie] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);

  const pageSize = 20;

  const classes = useStyles();

  const showMovieDetails = (mov) => {
    console.log("showMovieDetails ", mov);
    setTitle(mov.title);
    setDescription(mov.description);
    setDuration(mov.duration);
    setRanking(mov.ranking);
    setYear(mov.year);
    setGenres(mov.genres);
    setTrailer(mov.trailerUrl);
    setActors(mov.actors);
    setIsModalOpen(true);
  };

  const hideMovieDetails = () => {
    setIsModalOpen(false);
  };

  useEffect(() => {
    // window.onscroll = handleScroll;
    userApi
      .getMovies(titleSearch, genreSearch, currentPage, pageSize)
      .then((res) => res.json())
      .then((json) => {
        setMovies(json.content);
        // setNumberOfElements(json.totalElements);
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [titleSearch, genreSearch, currentPage]);

  //   useEffect(() => {
  //     return () => {
  //       window.removeEventListener("scroll", handleScroll);
  //     };
  //   });

  //   const handleScroll = () => {
  //     setCurrentPage(currentPage + 1);
  //     if (!topMovies.isLoading) {
  //       let percentageScrolled = getScrollPosition(window);
  //       if (percentageScrolled > 0.7) {
  //         const nextPage = currentPage + 1;
  //         setCurrentPage(nextPage);
  //       }
  //     }
  //   };

  const handleChangeTitle = (event) => {
    const titleSearchText = event.target.value;
    if (titleSearchText && (titleSearchText.length > 2 || genreSearch)) {
      setTitleSearch(titleSearchText);
    } else if (!titleSearchText) {
      setTitleSearch(null);
    }
  };

  const handleSelectGenre = (event) => {
    setGenreSearch(event.target.value);
  };

  return (
    <div>
      <Container>
        <Row>
          <TextField
            id="outlined-basic"
            placeholder="Movie title"
            label="Movie title"
            className="mb-3"
            onChange={handleChangeTitle}
            style={{
              margin: "0 auto auto auto",
              marginLeft: "10%",
              maxWidth: 200,
            }}
            variant="outlined"
          />
          <FormControl
            variant="outlined"
            className={classes.formControl}
            style={{
              margin: "0 auto",
              marginRight: "50%",
              maxWidth: 200,
              minWidth: 150,
            }}
          >
            <InputLabel id="demo-simple-select-outlined-label">
              Genre
            </InputLabel>
            <Select
              labelId="demo-simple-select-outlined-label"
              id="demo-simple-select-outlined"
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
        </Row>
        <Row>
          <UserMovieList
            movies={movies}
            isLoaded={isLoaded}
            showMovieDetails={showMovieDetails}
          />
        </Row>
      </Container>
      <UserMovieDetails
        title={title}
        description={description}
        duration={duration}
        year={year}
        ranking={ranking}
        genres={genres}
        trailer={trailer}
        actors={actors}
        isOpen={isModalOpen}
        hideMovieDetails={hideMovieDetails}
        isLoading={isLoadingMovie}
      />
    </div>
  );
};

export default UserMoviePage;
