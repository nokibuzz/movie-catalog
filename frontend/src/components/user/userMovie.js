import React, { useState } from "react";
import _ from "lodash";
import { Card, CardTitle, CardMedia } from "material-ui";

const styles = {
  cardTitle: {
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
    overflow: "hidden",
  },
  cardMedia: {
    maxHeight: 394,
    overflow: "hidden",
  },
  card: {
    cursor: "pointer",
    height: 400,
    overflow: "hidden",
  },
  bgImage: {
    width: "100%",
    height: "340px",
  },
};

const UserMovie = (props) => {
  const { movie, showMovieDetails } = props;
  const [isMouseOver, setIsMouseOver] = useState(false);

  const subtitle = isMouseOver ? movie.description : null;

  const movieImage = () => {
    // btoa(String.fromCharCode(...new Uint8Array(movie)));
    if (movie.image) {
      const file = movie.image.data;
      return `data:image/jpeg;base64,${file}`;
    }
    return "https://www.eglsf.info/wp-content/uploads/image-missing.png";
  };

  return (
    <Card
      style={styles.card}
      onMouseOver={() => setIsMouseOver(true)}
      onMouseLeave={() => setIsMouseOver(false)}
      onClick={() => showMovieDetails(movie)}
    >
      {<CardTitle title={<div style={styles.cardTitle}>{movie.title}</div>} />}
      <CardMedia
        style={styles.cardMedia}
        overlay={<CardTitle title={movie.title} subtitle={subtitle} />}
      >
        <img style={styles.bgImage} src={movieImage()} />
      </CardMedia>
    </Card>
  );
};

export default UserMovie;
