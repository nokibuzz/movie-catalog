import { keys } from "../common/action-keys";

// Opens the <MovieDetails /> with a movieId
export const showMovieDetails = (movieId) => {
  return {
    type: keys.SHOW_MOVIE_DETAILS,
    movieId,
  };
};

// Closes the <MovieDetails />
export const hideMovieDetails = () => {
  return {
    type: keys.HIDE_MOVIE_DETAILS,
  };
};
