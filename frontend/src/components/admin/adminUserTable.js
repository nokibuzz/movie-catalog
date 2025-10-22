import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import Paper from "@material-ui/core/Paper";
import LinearProgress from "@material-ui/core/LinearProgress";
import TextField from "@material-ui/core/TextField";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";

import {
  StyledTableRow,
  StyledTableCell,
} from "../../components/common/styled-table";

import adminApi from "../../api/admin-user-api";

const columns = [
  { id: "id", label: "ID", minWidth: 50 },
  { id: "name", label: "Name", minWidth: 170 },
  { id: "username", label: "Username", minWidth: 170 },
  { id: "email", label: "Email", minWidth: 170 },
  {
    id: "banned",
    label: "Status",
    minWidth: 100,
    format: (value) => (value ? "Banned" : "Active"),
  },
];

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
  },
  container: {
    maxHeight: 650,
  },
  progress: {
    width: "100%",
    "& > * + *": {
      marginTop: theme.spacing(2),
    },
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  small: {
    width: theme.spacing(3),
    height: theme.spacing(3),
    marginTop: theme.spacing(1),
  },
}));

const AdminUserTable = () => {
  const classes = useStyles();

  const [page, setPage] = useState(0);
  const [numberOfElements, setNumberOfElements] = useState(10);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [users, setUsers] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [username, setUsername] = useState(null);
  const [trigger, setTrigger] = useState(false);

  useEffect(() => {
    adminApi
      .getUsers(username, page, rowsPerPage)
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        setUsers(json.content);
        setNumberOfElements(json.totalElements);
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [username, page, rowsPerPage, trigger]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const handleChangeUsername = (event) => {
    const usernameSearch = event.target.value;
    console.log(usernameSearch);
    if (usernameSearch && usernameSearch.length > 2) {
      setUsername(usernameSearch);
    } else if (!usernameSearch) {
      setUsername(null);
    }
  };

  const handleBan = (user) => {
    adminApi
      .banOrUnbanUser(user.username, user.banned)
      .then(() => {
        setTrigger(!trigger);
      })
      .catch(console.log("Error banning"));
  };

  if (!isLoaded)
    return (
      <div className={classes.progress}>
        <p>Loading...</p>
        <LinearProgress />
      </div>
    );

  return (
    <Paper className={classes.root}>
      <TextField
        id="outlined-basic"
        placeholder="Username"
        label="Username"
        className="mb-3"
        onChange={handleChangeUsername}
        style={{
          margin: "0 auto",
          maxWidth: 300,
        }}
        variant="outlined"
      />
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <StyledTableRow>
              {columns.map((column) => (
                <StyledTableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </StyledTableCell>
              ))}
              <StyledTableCell>Avatar</StyledTableCell>
              <StyledTableCell></StyledTableCell>
              <StyledTableCell></StyledTableCell>
            </StyledTableRow>
          </TableHead>
          <TableBody>
            {users.map((row) => {
              return (
                <StyledTableRow
                  hover
                  role="checkbox"
                  tabIndex={-1}
                  key={row.id}
                >
                  {columns.map((column) => {
                    const value = row[column.id];
                    return (
                      <StyledTableCell key={column.id} align={column.align}>
                        {column.format ? column.format(value) : value}
                      </StyledTableCell>
                    );
                  })}
                  <StyledTableCell>
                    <Avatar
                      alt="avatar"
                      src={row.avatarURL}
                      className={classes.small}
                    />
                  </StyledTableCell>
                  <StyledTableCell>
                    <Button
                      color="primary"
                      style={{ outline: "none" }}
                      onClick={() => handleBan(row)}
                    >
                      {row.banned ? "Unban" : "Ban"}
                    </Button>
                  </StyledTableCell>
                </StyledTableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[1, 2, 5, 10, 20, 50]}
        component="div"
        count={numberOfElements}
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default AdminUserTable;
