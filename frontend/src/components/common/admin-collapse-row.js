import React, { Fragment, useState } from "react";
import Box from "@material-ui/core/Box";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableBody from "@material-ui/core/TableBody";
import Collapse from "@material-ui/core/Collapse";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";
import DeleteForeverOutlinedIcon from "@material-ui/icons/DeleteForeverOutlined";
import EditRoundedIcon from "@material-ui/icons/EditRounded";
import Rating from "@material-ui/lab/Rating";
import Moment from "moment";

import { StyledTableRow, StyledTableCell } from "./styled-table";
import ClickableChip from "./clickableChip";

const AdminCollapseRow = (props) => {
  const { row, columns, handleUpdate, handleDelete, handleClickGenre } = props;
  const [open, setOpen] = useState(false);

  const movieImage = () => {
    console.log(row.image);
    if (row.image) {
      const file = row.image.data;
      return `data:image/jpeg;base64,${file}`;
    }
    return "https://www.eglsf.info/wp-content/uploads/image-missing.png";
  };

  return (
    <Fragment>
      <StyledTableRow>
        <StyledTableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
            style={{ outline: "none" }}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </StyledTableCell>
        {columns.map((column) => {
          const value = row[column.id];
          return (
            <StyledTableCell key={column.id} align={column.align}>
              {column.format && typeof value === "number"
                ? column.format(value)
                : column.isList
                ? value.map((item) => {
                    return (
                      <ClickableChip item={item} action={handleClickGenre} />
                    );
                  })
                : value}
            </StyledTableCell>
          );
        })}
        <StyledTableCell>
          <Rating value={row.ranking} precision={0.5} readOnly />
        </StyledTableCell>
        <StyledTableCell>
          <IconButton
            title="Delete movie"
            aria-label="expand row"
            size="small"
            style={{ outline: "none" }}
            onClick={() => handleDelete(row)}
          >
            <DeleteForeverOutlinedIcon />
          </IconButton>
        </StyledTableCell>
        <StyledTableCell>
          <IconButton
            title="Edit movie"
            aria-label="expand row"
            size="small"
            style={{ outline: "none" }}
            onClick={() => handleUpdate(row)}
          >
            <EditRoundedIcon />
          </IconButton>
        </StyledTableCell>
      </StyledTableRow>
      <StyledTableRow>
        <StyledTableCell
          style={{ paddingBottom: 1, paddingTop: 1 }}
          colSpan={4}
        >
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Typography variant="h6" gutterBottom component="div">
                <center>
                  <img
                    style={{ width: "150px", height: "200px" }}
                    src={movieImage()}
                  />
                </center>
              </Typography>
            </Box>
            <Box margin={1}>
              <Typography
                variant="h6"
                gutterBottom
                component="div"
                style={{ color: "red" }}
                align="center"
              >
                Actors
              </Typography>
            </Box>
            <Table size="small" aria-label="purchases">
              <TableHead>
                <StyledTableRow>
                  <StyledTableCell
                    style={{ backgroundColor: "red" }}
                    align="left"
                  >
                    ID
                  </StyledTableCell>
                  <StyledTableCell
                    style={{ backgroundColor: "red" }}
                    align="left"
                  >
                    Name
                  </StyledTableCell>
                  <StyledTableCell
                    style={{ backgroundColor: "red" }}
                    align="left"
                  >
                    Birthday
                  </StyledTableCell>
                  <StyledTableCell
                    style={{ backgroundColor: "red" }}
                    align="left"
                  >
                    Roles
                  </StyledTableCell>
                </StyledTableRow>
              </TableHead>
              {
                <TableBody>
                  {row.actors.map((actor) => {
                    return (
                      <StyledTableRow key={actor.id}>
                        <StyledTableCell component="th" scope="row">
                          {actor.id}
                        </StyledTableCell>
                        <StyledTableCell>{actor.name}</StyledTableCell>
                        <StyledTableCell>
                          {Moment(actor.birthday).format("DD/MM/YYYY")}
                        </StyledTableCell>
                        <StyledTableCell>{actor.roles}</StyledTableCell>
                      </StyledTableRow>
                    );
                  })}
                </TableBody>
              }
            </Table>
          </Collapse>
        </StyledTableCell>
      </StyledTableRow>
    </Fragment>
  );
};

export default AdminCollapseRow;
