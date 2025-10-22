import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import LinearProgress from "@material-ui/core/LinearProgress";

import adminApi from "../../api/admin-user-api";
import AuthService from "../../api/auth-api";
import { Avatar } from "material-ui";
import Typography from "material-ui/styles/typography";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: "center",
    color: theme.palette.text.secondary,
    background: "transparent",
  },
  progress: {
    width: "100%",
    "& > * + *": {
      marginTop: theme.spacing(2),
    },
  },
}));

const Profile = () => {
  const [user, setUser] = useState(null);
  const currentUser = AuthService.getCurrentUser();
  const [isLoaded, setIsLoaded] = useState(false);

  const classes = useStyles();

  useEffect(() => {
    adminApi
      .getUser(currentUser.username)
      .then((res) => res.json())
      .then((json) => {
        setUser(json);
        setIsLoaded(true);
      })
      .catch(console.log("Error getting user"));
  }, []);

  if (!isLoaded)
    return (
      <div className={classes.progress}>
        <p>Loading...</p>
        <LinearProgress />
      </div>
    );

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <div className={classes.root}>
            <Grid container spacing={5}>
              <Grid item xs={3}>
                <Paper className={classes.paper}>
                  <Avatar
                    alt="avatar"
                    src={currentUser.avatarURL}
                    style={{ width: "200px", height: "200px" }}
                  />
                </Paper>
              </Grid>
              <Grid item xs={9} container direction="column">
                <Grid item xs>
                  <Paper className={classes.paper}>{user.username}</Paper>
                </Grid>
                <Grid item xs>
                  <Paper className={classes.paper}>{user.name}</Paper>
                </Grid>
                <Grid item xs>
                  <Paper className={classes.paper}>{user.email}</Paper>
                </Grid>
                <Grid item xs>
                  <Paper className={classes.paper}>
                    {currentUser.roles &&
                      currentUser.roles.map((role, index) => (
                        <p key={index}>{role}</p>
                      ))}
                  </Paper>
                </Grid>
              </Grid>
            </Grid>
          </div>
        </h3>
      </header>
    </div>
  );
};

export default Profile;
