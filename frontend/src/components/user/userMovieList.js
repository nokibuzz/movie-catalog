import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Row, Col } from "react-bootstrap";
import UserMovie from "./userMovie";
import Loader from "../common/loader";
import LinearProgress from "@material-ui/core/LinearProgress";
import adminApi from "../../api/admin-movie-api";
import infiniteScrollMovies from "../../helper/infinite-scroll";

const styles = makeStyles((theme) => ({
  movieColumn: {
    marginBottom: 20,
  },
  progress: {
    width: "100%",
    "& > * + *": {
      marginTop: theme.spacing(2),
    },
  },
}));
const UserMovieList = (props) => {
  const { movies, isLoaded, showMovieDetails } = props;

  // const [movies, setMovies] = useState([]);
  // const [isLoaded, setIsLoaded] = useState(false);
  // const [currentPage, setCurrentPage] = useState(0);

  const classes = styles();

  const pageSize = 20;

  // useEffect(() => {
  //   // window.onscroll = handleScroll;
  //   adminApi
  //     .getMovies(null, null, currentPage, pageSize)
  //     .then((res) => res.json())
  //     .then((json) => {
  //       setMovies(json.content);
  //       // setNumberOfElements(json.totalElements);
  //       setIsLoaded(true);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, [currentPage]);

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

  if (!isLoaded)
    return (
      <div className={classes.progress}>
        <p>Loading...</p>
        <LinearProgress />
      </div>
    );

  return (
    <Row>
      {movies.map((movie) => (
        <Col
          style={styles.movieColumn}
          key={movie.id}
          xs={12}
          sm={4}
          md={3}
          lg={3}
        >
          <UserMovie movie={movie} showMovieDetails={showMovieDetails} />
        </Col>
      ))}
    </Row>
  );
};

export default UserMovieList;
