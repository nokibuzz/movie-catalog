import React from "react";
import { connect } from "react-redux";
import { Dialog } from "material-ui";
import _ from "lodash";
import { hideMovieDetails } from "../../actions/movie-details-action";
import { getMovieDetails } from "../../actions/movie-browser-action";
import * as movieHelper from "../../helper/movies-helper";
import Loader from "../common/loader";

const styles = {
  // Can use functions to dynamically build our CSS
  dialogContent: (backgroundUrl) => ({
    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url(${backgroundUrl})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100%",
    height: "100%",
    minHeight: 400,
    color: "white",
    padding: 10,
  }),
};

class MovieDetails extends React.Component {
  // Triggered right after a property is changed
  componentWillReceiveProps(nextProps) {
    // If we will receive a new movieId
    if (nextProps.movieId && this.props.movieId !== nextProps.movieId) {
      nextProps.getMovieDetails(nextProps.movieId);
    }
  }

  render() {
    const { isOpen, hideMovieDetails, isLoading } = this.props;
    const loadingStatus = isLoading ? "loading" : "hide";
    const movie = movieHelper.updateMoviePictureUrls(this.props.movie);
    const genres =
      movie && movie.genres
        ? movie.genres.map((genre) => genre.name).join(", ")
        : "";

    return (
      <Dialog
        autoScrollBodyContent={true}
        title={null}
        modal={false}
        open={isOpen}
        onRequestClose={hideMovieDetails}
      >
        <Loader isLoading={isLoading}>
          <h1>Please login to use additional features</h1>
        </Loader>
      </Dialog>
    );
  }
}
// "connect" our movie modal to the component store
export default connect(
  // Map nodes in our state to a properties of our component
  (state) => ({
    // Using lodash get, recursively check that a property is defined
    // before try to access it - if it is undefined, it will return your default value
    // _.get(object, 'path.to.targets[0].neat.stuff', defaultValue)
    isOpen: _.get(state, "movieBrowser.movieDetailsModal.isOpen", false),
    movieId: _.get(state, "movieBrowser.movieDetailsModal.movieId"),
    movie: _.get(state, "movieBrowser.movieDetails", {}),
    isLoading: _.get(state, "movieBrowser.movieDetails.isLoading", false),
  }),
  // Map an action to a prop, ready to be dispatched
  { hideMovieDetails, getMovieDetails }
)(MovieDetails);
