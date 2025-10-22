import React, { useEffect } from "react";
import { Dialog } from "material-ui";
import _ from "lodash";
import Rating from "@material-ui/lab/Rating";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Loader from "../common/loader";

const styles = {
  dialogContent: {
    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url(https://cdn5.f-cdn.com/contestentries/1476645/3722903/5c790acfaed94_thumb900.jpg)`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100%",
    height: "100%",
    minHeight: "100%",
    minHeight: 400,
    color: "white",
    padding: 10,
  },
};

const YouTube = (props) => {
  const { video, autoplay, rel, modest } = props;

  console.log("Movie ", props);

  const videoSrc =
    "https://www.youtube.com/embed/" +
    video +
    "?autoplay=" +
    autoplay +
    "&rel=" +
    rel +
    "&modestbranding=" +
    modest;
  return (
    <div
      className="container"
      style={{
        width: "400px",
        height: "400px",
        position: "absolute",
        top: "23px",
        right: "10px",
      }}
    >
      <iframe
        className="player"
        type="text/html"
        width="100%"
        height="100%"
        src={videoSrc}
        frameborder="2"
      />
    </div>
  );
};

const UserMovieDetails = (props) => {
  const {
    title,
    description,
    duration,
    year,
    ranking,
    genres,
    trailer,
    actors,
    isOpen,
    hideMovieDetails,
    isLoading,
  } = props;
  const loadingStatus = isLoading ? "loading" : "hide";
  const genresToShow = genres ? genres.map((genre) => genre).join(", ") : "";

  const formatDuration = (time) => {
    console.log(time);
    return (
      Math.floor(time / 60) +
      ":" +
      (time % 60 > 9 ? time % 60 : "0" + (time % 60))
    );
  };

  return (
    <Dialog
      autoScrollBodyContent={true}
      title={null}
      modal={false}
      open={isOpen}
      onRequestClose={hideMovieDetails}
    >
      <Loader isLoading={isLoading}>
        <div style={styles.dialogContent}>
          <div
            style={{ display: "flex", flexDirection: "column", width: "340px" }}
          >
            <h5 style={{ color: "#808080" }}>{title}</h5>
            <h5 style={{ color: "#585858" }}>{genresToShow}</h5>
            <h5 style={{ color: "#A9A9A9" }}>{description}</h5>
            <h5 style={{ color: "#696969" }}>
              Running time: {formatDuration(duration)}
            </h5>
            <h5>
              Rating: <Rating value={ranking} precision={0.5} readOnly />
            </h5>
            <List>
              {actors.map((actor) => {
                return (
                  <ListItem>
                    <ListItemText
                      style={{ color: "#A9A9A9" }}
                      primary={actor.name}
                    />
                  </ListItem>
                );
              })}
            </List>
          </div>

          <YouTube video={trailer} autoplay="1" rel="0" modest="1" />
        </div>
      </Loader>
    </Dialog>
  );
};

export default UserMovieDetails;
