import Table from 'react-bootstrap/Table';
import {Card} from "react-bootstrap";
import {HiViewList, HiEye, HiX} from 'react-icons/hi';
import {GrUpdate} from 'react-icons/gr';
import axios from "axios";
import {useEffect, useState} from "react";
import PatientToast from "./PatientToast";
import {Link, useParams} from "react-router-dom";
import ViewPatient from "./ViewPatient";

const PatientList = () => {
    const patientBaseURL = "http://localhost:8080/patients"
    const [patients, setPatients] = useState([]) 
    const [show, setShow] = useState(false)
    const [message, setMessage] = useState("")

    const [viewModal, setViewModal] = useState(false);

    useEffect(() => {
        axios.get(patientBaseURL).then((response) => {
            setPatients(response.data);
        })
    }, []);

    const deletePatient = (patient, patientId) => {
        axios.delete(patientBaseURL + "/" + patientId)
            .then((response) => {
            setShow(true)
            setMessage("Deleted Patient: " + patient.firstName + " " + patient.lastName)
            setPatients(patients.filter(patient => patient.patientId !== patientId))
            setTimeout(() => setShow(false), 3000)
        })
            .catch( function (reason) {
                setShow(true)
                setMessage("Cant Delete Patient: " + patient.firstName + " " + patient.lastName)
                setTimeout(() => setShow(false), 3000)
            })
    }

    function openModal() {
        setViewModal(!viewModal)
    }

    return (
        <div className="container">
            <div style={show ? {visibility: "visible"} : {visibility: "hidden"}}>
                <PatientToast isOpen={show} message={message} bgColor="#FA8072" textColor="#990F02"/>
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header style={{display: "flex"}}>
                    <HiViewList size={22}/> List Patient
                </Card.Header>
                <Card.Body>
                    <div className="text-end">
                        <p>
                            Total Patients: {patients.length}
                        </p>
                    </div>
                    <Table bordered hover striped variant={"dark"}>
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Sex</th>
                            <th>Age</th>
                            <th>Contact #</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {patients.map((patient, index) =>
                            <tr key={index}>
                                <td>{patient.firstName}</td>
                                <td>{patient.lastName}</td>
                                <td>{patient.sex}</td>
                                <td>{patient.age}</td>
                                <td>{patient.contactInfo?.mobileNumber}</td>
                                <td style={{width: "25%"}}>
                                    <div className="text-center">
                                        <Link to={"/view?id=" + patient.patientId}>
                                            <button className="mx-1" onClick={openModal}><HiEye
                                                color="gray"/> View
                                            </button>
                                        </Link>
                                        <button className="mx-1" onClick={() => deletePatient(patient, patient.patientId)}><HiX
                                            color="red"/> Delete
                                        </button>
                                        <Link to={"/edit/" + patient.patientId}>
                                            <button className="mx-1">
                                                <GrUpdate color="red"/> Update
                                            </button>
                                        </Link>
                                    </div>
                                </td>
                            </tr>
                        )}
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </div>

    )
}

export default PatientList