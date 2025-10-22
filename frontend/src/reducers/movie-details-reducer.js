import { keys } from "../common/action-keys";
import { createReducer } from "../common/redux-helper";

// Placeholder reducer for our movie modal
const movieDetailsReducer = createReducer(
  { isOpen: false, movieId: undefined },
  {
    [keys.SHOW_MOVIE_DETAILS]: (state, action) => ({
      isOpen: true,
      movieId: action.movieId,
    }),
    [keys.HIDE_MOVIE_DETAILS]: (state, action) => ({
      // Persist the movieId (and any other state tree objects)
      ...state,
      isOpen: false,
    }),
  }
);

export default movieDetailsReducer;
