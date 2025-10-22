import React, { Component, useState, useEffect } from "react";
import { update } from "lodash";
import adminApi from "../../api/admin-api";

const AdminMovieTry = () => {
  //   constructor(props) {
  //     super(props);

  //     this.state = {
  //       items: [],
  //       isLoaded: false,
  //     };
  //   }

  const [items, setItems] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);

  /**
   * componentDidMount
   *
   * Fetch json array of objects from given url and update state.
   */
  //componentDidMount() {
  useEffect(() => {
    // fetch("http://localhost:8080/v1/admin/movie")
    adminApi
      .getMovies()
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
        setItems(json.content);
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  //}

  /**
   * render
   *
   * Render UI
   */
  //render() {

  if (!isLoaded) return <div>Loading...</div>;

  return (
    <div className="App">
      <ul>
        {items.map((item) => (
          <li key={item.id}>
            Name: {item.title} | Email: {item.genre}
          </li>
        ))}
      </ul>
    </div>
  );
  // }
};

export default AdminMovieTry;
