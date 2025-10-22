import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";

import MainPage from "./components/MainPage";
import Login from "./components/auth/login";
import SignUp from "./components/auth/signup";
import Profile from "./components/auth/profile";
import AdminMovies from "./components/admin/adminMovies";
import AdminActors from "./components/admin/adminActors";
import AdminUsers from "./components/admin/adminUsers";
import AuthService from "./api/auth-api";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import UserMoviePage from "./components/user/userMoviesPage";

const useStyles = makeStyles((theme) => ({
  small: {
    width: theme.spacing(3),
    height: theme.spacing(3),
    marginTop: theme.spacing(1),
  },
}));

const App = () => {
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);
  const [anchorEl, setAnchorEl] = useState(null);

  const classes = useStyles();

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <MuiThemeProvider>
      <Router>
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/"} className="navbar-brand">
              Movie App
            </Link>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/home"} className="nav-link">
                  Home
                </Link>
              </li>

              {showAdminBoard && (
                <div className="nav-item mr-auto">
                  <li
                    className="nav-link"
                    onClick={handleClick}
                    style={{ cursor: "pointer" }}
                  >
                    Admin Board
                  </li>
                  <Menu
                    id="simple-menu"
                    anchorEl={anchorEl}
                    keepMounted
                    open={Boolean(anchorEl)}
                    onClose={handleClose}
                  >
                    <MenuItem className="nav-link" onClick={handleClose}>
                      <Link
                        className="nav-link text-secondary"
                        to={"/admin/movies"}
                      >
                        Movies
                      </Link>
                    </MenuItem>
                    <MenuItem className="nav-link" onClick={handleClose}>
                      <Link
                        className="nav-link  text-secondary"
                        to={"/admin/actors"}
                      >
                        Actors
                      </Link>
                    </MenuItem>
                    <MenuItem className="nav-link" onClick={handleClose}>
                      <Link
                        className="nav-link  text-secondary"
                        to={"/admin/users"}
                      >
                        Users
                      </Link>
                    </MenuItem>
                  </Menu>
                </div>
              )}

              {currentUser && (
                <li className="nav-item">
                  <Link to={"/user"} className="nav-link">
                    User
                  </Link>
                </li>
              )}
            </div>

            {currentUser ? (
              <div className="navbar-nav ml-auto">
                <Avatar
                  alt="avatar"
                  src={currentUser.avatarURL}
                  className={classes.small}
                />
                <li className="nav-item">
                  <Link to={"/profile"} className="nav-link">
                    {currentUser.username}
                  </Link>
                </li>
                <li className="nav-item">
                  <a href="/login" className="nav-link" onClick={logOut}>
                    LogOut
                  </a>
                </li>
              </div>
            ) : (
              <div className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link to={"/login"} className="nav-link">
                    Login
                  </Link>
                </li>

                <li className="nav-item">
                  <Link to={"/register"} className="nav-link">
                    Sign Up
                  </Link>
                </li>
              </div>
            )}
          </nav>

          <div className="container mt-3">
            <Switch>
              <Route exact path={["/", "/home"]} component={MainPage} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={SignUp} />
              <Route exact path="/profile" component={Profile} />
              <Route path="/user" component={UserMoviePage} />
              <Route path="/admin/movies" component={AdminMovies} />
              <Route path="/admin/actors" component={AdminActors} />
              <Route path="/admin/users" component={AdminUsers} />
            </Switch>
          </div>
        </div>
      </Router>
    </MuiThemeProvider>
  );
};

export default App;
