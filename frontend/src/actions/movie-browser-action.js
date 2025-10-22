import { createAsyncActionCreator } from "../common/redux-helper";
import * as movieService from "../api/movie-api";
import { keys } from "../common/action-keys";

export const getTopMovies = (page) =>
  createAsyncActionCreator(
    // actionType
    keys.GET_TOP_MOVIES,
    // requestFn
    movieService.getTopMovies,
    // requestParams
    { page }
  );

export const searchMovies = (query, page) =>
  createAsyncActionCreator(keys.SEARCH_MOVIES, movieService.searchMovies, {
    query,
    page,
  });

export const getMovieDetails = (movieId) =>
  createAsyncActionCreator(
    keys.GET_MOVIE_DETAILS,
    movieService.getMovieDetails,
    { movieId }
  );
