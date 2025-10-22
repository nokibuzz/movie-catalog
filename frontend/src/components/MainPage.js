import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { AppBar } from "material-ui";
import MovieList from "../components/movie/movieList";
import MovieDetails from "../components/movie/movieDetails";
import { connect } from "react-redux";
import * as movieActions from "../actions/movie-browser-action";
import * as movieHelper from "../helper/movies-helper";
import getScrollPosition from "../helper/infinite-scroll";

class MainPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentPage: 1,
    };
    // Binds the handleScroll to this class (MovieBrowser)
    // which provides access to MovieBrowser's props
    // Note: You don't have to do this if you call a method
    // directly from a lifecycle method or define an arrow function
    this.handleScroll = this.handleScroll.bind(this);
  }

  componentDidMount() {
    window.onscroll = this.handleScroll;
    this.props.getTopMovies(this.state.currentPage);
  }

  componentWillUnmount() {
    window.removeEventListener("scroll", this.handleScroll);
  }

  handleScroll() {
    const { topMovies } = this.props;
    if (!topMovies.isLoading) {
      let percentageScrolled = getScrollPosition(window);
      if (percentageScrolled > 0.7) {
        const nextPage = this.state.currentPage + 1;
        this.props.getTopMovies(nextPage);
        this.setState({ currentPage: nextPage });
      }
    }
  }

  render() {
    const { topMovies } = this.props;
    const movies = movieHelper.getMoviesList(topMovies.response);

    return (
      <div>
        <Container>
          <Row>
            <MovieList movies={movies} isLoading={topMovies.isLoading} />
          </Row>
        </Container>
        <MovieDetails />
      </div>
    );
  }
}

export default connect(
  // Map nodes in our state to a properties of our component
  (state) => ({
    topMovies: state.movieBrowser.topMovies,
  }),
  // Map action creators to properties of our component
  { ...movieActions }
)(MainPage);
