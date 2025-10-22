import React from "react";
import { Container, Row } from "react-bootstrap";
import AdminUserTable from "./adminUserTable";

const AdminUsers = () => {
  return (
    <div>
      <h1>Manage Users</h1>
      <Container>
        <Row>
          <AdminUserTable />
        </Row>
      </Container>
    </div>
  );
};

export default AdminUsers;
