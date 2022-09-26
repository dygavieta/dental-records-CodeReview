import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Image} from "react-bootstrap";
import dental_logo from '../Assets/dental-logo.png';
import {Link} from "react-router-dom";

const NavBar = () => {
    return (
        <div>
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Link to={"/"}>
                        <Navbar.Brand> <Image src={dental_logo} style={{width: 60}}></Image></Navbar.Brand>
                    </Link>
                    <Nav className="me-auto">
                        <Link to={"/list"} className={"nav-link"}>
                            Patient List
                        </Link>
                        <Link to={"/list/dentist"} className={"nav-link"}>
                            Dentist List
                        </Link>
                        <Link to={"/add"} className={"nav-link"}>
                            Add Patient
                        </Link>
                    </Nav>
                </Container>
            </Navbar>
            <br/>
        </div>
    )
}

export default NavBar
