import React, { useState, useEffect } from "react";
import { Container, Row } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { green } from "@material-ui/core/colors";
import AddIcon from "@material-ui/icons/Add";
import Fab from "@material-ui/core/Fab";
import Tooltip from "@material-ui/core/Tooltip";
import AdminActorTable from "./adminActorTable";

import AddActor from "./addActor";
import adminApi from "../../api/admin-actor-api";

const useStyles = makeStyles((theme) => ({
  absolute: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3),
  },
}));

const AdminActors = () => {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [fetchActors, setFetchActors] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSave = (actor) => {
    adminApi
      .addActor(actor)
      .then(() => {
        setFetchActors(!fetchActors);
        handleClose();
      })
      .catch(console.log("Nooo"));
  };

  return (
    <div>
      <h1>Actors</h1>
      <Tooltip title="Add actor" aria-label="add">
        <Fab
          style={{ background: green[500], color: "white", outline: "none" }}
          className={classes.absolute}
          onClick={handleOpen}
        >
          <AddIcon />
        </Fab>
      </Tooltip>
      <Container>
        <Row>
          <AdminActorTable fetchActors={fetchActors} />
        </Row>
      </Container>
      <AddActor open={open} handleClose={handleClose} handleSave={handleSave} />
    </div>
  );
};

export default AdminActors;
