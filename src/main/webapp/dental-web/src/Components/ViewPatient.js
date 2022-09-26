import React, {useEffect, useState} from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {FaTimes} from "react-icons/fa";
import {Link, useLocation, useParams, useSearchParams} from "react-router-dom";
import axios from "axios";
import Form from 'react-bootstrap/Form';
import Accordion from 'react-bootstrap/Accordion';
import {Card, FormText} from "react-bootstrap";
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Table from 'react-bootstrap/Table';

const ViewPatient = (props) => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [onShow, setOnShow] = useState(true)
    const patientId = searchParams.get('id');
    console.log(patientId)
    const [patient, setPatient] = useState("")
    const [fullName, setFullName] = useState("");
    const [address, setAddress] = useState("");
    useEffect(() => {
        axios.get("http://localhost:8080/patients/" + patientId)
            .then(response => {
                console.log((response.data))
                setPatient(response.data)
                setFullName(response.data.firstName + " "
                    + response.data.middleInitial + " "
                    + response.data.lastName)
                setAddress(
                    response.data.contactInfo?.baranggay
                    + " " + response.data.contactInfo?.city
                    + " " + response.data.contactInfo?.region
                )
            })
    }, [])
    return (
        <div>
            <Modal show={onShow} size={"xl"}>
                <Modal.Header>
                    <Modal.Title>
                        <h2>Patient{" #"}{patient.patientId}</h2>
                    </Modal.Title>
                    <Link to={"/list"}>
                        <FaTimes onClick={() => setOnShow(!onShow)}
                                 className="exit-btn-icon"/>
                    </Link>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <fieldset disabled={true}>
                            <div style={{display: "flex"}}>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={fullName}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Sex</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.sex}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Age</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.age}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Nationality</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.nationality}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Occupation</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.occupation}/>
                                </Form.Group>
                            </div>
                            <div style={{display: "flex"}}>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={address}/>
                                </Form.Group>
                                <Form.Group className=" mb-3 px-2">
                                    <Form.Label>Mobile Number</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.contactInfo?.mobileNumber}/>
                                </Form.Group>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>Email Address</Form.Label>
                                    <Form.Control type="text"
                                                  defaultValue={patient.contactInfo?.emailAddress}/>
                                </Form.Group>
                            </div>
                            <div className="mb-3">
                                <h4>Dental History</h4>
                                <FloatingLabel style={{color: "white"}} label={patient.dentalHistory?.description}>
                                    <Form.Control
                                        as="textarea"
                                        placeholder="Leave a comment here"
                                        style={{height: '60px'}}
                                    />
                                </FloatingLabel>
                            </div>
                            <div className="mb-3">
                                <h4>Treatments</h4>
                                <Table bordered size="sm" style={{color: "white"}}>
                                    <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Tooth #</th>
                                        <th>Description</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {patient.treatments?.map((treatment, index) =>
                                        <tr>
                                            <td>{treatment.date}</td>
                                            <td>{treatment.toothNumber}</td>
                                            <td>{treatment.description}</td>
                                        </tr>
                                    )}

                                    </tbody>
                                </Table>
                            </div>
                        </fieldset>
                    </Form>
                </Modal.Body>

            </Modal>
        </div>
    )
}

export default ViewPatient