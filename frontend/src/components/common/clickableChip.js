import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Chip from "@material-ui/core/Chip";

const useStyles = makeStyles((theme) => ({
  root: {
    margin: 2,
    "& > *": {
      margin: theme.spacing(1),
    },
  },
}));

const ClickableChip = (props) => {
  const classes = useStyles();
  const { item, action } = props;
  let colorChip;

  switch (item) {
    case "ACTION":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "red" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "COMEDY":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "#4169E1" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "HORROR":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "black" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "THRILLER":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "green" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "ROMANCE":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "#FF1493" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "CRIME":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "#9400D3" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "SCIENCEFICTION":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "coral" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "FAMILLY":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "#9BF0F5" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    case "DRAMA":
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "#9370DB" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
    default:
      colorChip = (
        <Chip
          className={classes.root}
          style={{ color: "orange" }}
          label={item}
          onClick={() => action(item)}
          variant="outlined"
        />
      );
      break;
  }

  return colorChip;
};

export default ClickableChip;
