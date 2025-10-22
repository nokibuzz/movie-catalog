import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";

const useStyles = makeStyles((theme) => ({
  modal: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #456",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  formControl: {
    margin: theme.spacing(0.5),
    width: "250px",
  },
}));

const AddActor = (props) => {
  const classes = useStyles();
  const { open, handleClose, handleSave } = props;
  const [name, setName] = useState("");
  const [birthday, setBirthday] = useState("");
  const [roles, setRoles] = useState([]);

  const handleChange = (event) => {
    switch (event.target.name) {
      case "Name":
        setName(event.target.value);
        break;
      case "Birthday":
        setBirthday(event.target.value);
        break;
      case "Roles":
        setRoles(event.target.value);
        break;
      default:
        break;
    }
  };

  const callSave = () => {
    const rolesToSend = roles.join(",");
    handleSave({
      name: name,
      birthday: birthday,
      roles: rolesToSend,
    });
  };

  return (
    <Modal
      aria-labelledby="transition-modal-title"
      aria-describedby="transition-modal-description"
      className={classes.modal}
      open={open}
      onClose={handleClose}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500,
      }}
    >
      <Fade in={open}>
        <div className={classes.paper}>
          <h2 id="transition-modal-title" align="center">
            Create actor
          </h2>
          <form
            className={classes.modal}
            noValidate
            autoComplete="off"
            onSubmit={callSave}
          >
            <TextField
              id="name"
              label="Name"
              name="Name"
              placeholder="Actor name"
              variant="outlined"
              className={classes.formControl}
              value={name}
              onChange={handleChange}
            />

            <TextField
              id="birthday"
              label="Birthday"
              name="Birthday"
              type="date"
              variant="outlined"
              format="dd/MM/yyyy"
              value={birthday}
              onChange={handleChange}
              className={classes.formControl}
              InputLabelProps={{
                shrink: true,
              }}
            />

            <FormControl
              variant="outlined"
              required
              className={classes.formControl}
            >
              <InputLabel id="demo-simple-select-outlined-label">
                Roles
              </InputLabel>
              <Select
                id="roles"
                label="Roles"
                name="Roles"
                labelId="roles"
                multiple
                value={roles}
                onChange={handleChange}
              >
                <MenuItem value="">
                  <em></em>
                </MenuItem>
                <MenuItem value="Actor">Actor</MenuItem>
                <MenuItem value="Director">Director</MenuItem>
                <MenuItem value="Screen writter">Screen writter</MenuItem>
                <MenuItem value="Producer">Producer</MenuItem>
                <MenuItem value="Music maker">Music maker</MenuItem>
              </Select>
            </FormControl>
          </form>

          <Button
            variant="contained"
            color="secondary"
            onClick={handleClose}
            style={{
              margin: 2,
              marginLeft: 50,
              marginTop: 20,
              outline: "none",
            }}
          >
            Close
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={callSave}
            style={{
              margin: 2,
              marginLeft: 10,
              marginTop: 20,
              outline: "none",
            }}
          >
            Save
          </Button>
        </div>
      </Fade>
    </Modal>
  );
};

export default AddActor;
