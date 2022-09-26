import Table from 'react-bootstrap/Table';
import {Card} from "react-bootstrap";
import {HiViewList, HiEye, HiX} from 'react-icons/hi';
import {FaTimes, FaCog} from 'react-icons/fa';
import axios from "axios";
import {useEffect, useState} from "react";
import PatientToast from "./PatientToast";
import Accordion from 'react-bootstrap/Accordion';
import Button from "react-bootstrap/Button";

const DentistList = () => {
    const dentistBaseURL = "http://localhost:8080/dentists"
    const [dentists, setDentists] = useState([])
    const [patients, setPatients] = useState([])
    const [show, setShow] = useState(false)
    const [message, setMessage] = useState("")


    useEffect(() => {
        axios.get(dentistBaseURL).then((response) => {
            setDentists(response.data);
        })
    }, []);

    const removePatientFromDentist = (dentist, patient, dentistId, patientId) => {
        setPatients(dentist.patient)
        axios.delete(dentistBaseURL + "/" + dentistId + "/patients/" + patientId)
            .then((response) => {
                setShow(true)
                setPatients(patients.filter(patient => patient.patientId !== patientId))
                setMessage("Removed Patient: " + patient.firstName + " " + patient.lastName)
                setTimeout(() => setShow(false), 3000)
            })
            .catch(function (reason) {
                setShow(true)
                setMessage("Cant Remove Patient: " + patient.firstName + " " + patient.lastName)
                setTimeout(() => setShow(false), 3000)
            })
    }

    const deleteDentist = (dentist, dentistId) =>{
        axios.delete(dentistBaseURL + "/" + dentistId)
            .then((response) => {
                setShow(true)
                setDentists(dentists.filter(dentist => dentist.dentistId !== dentistId))
                setMessage("Removed Dentist: Dr. " + dentist.firstName + " " + dentist.lastName)
                setTimeout(() => setShow(false), 3000)
            })
            .catch(function (reason) {
                setShow(true)
                setMessage("Cant Remove Dentist: Dr. " + dentist.firstName + " " + dentist.lastName)
                setTimeout(() => setShow(false), 3000)
            })
    }
    return (
        <div className="container">
            <div style={show ? {visibility: "visible"} : {visibility: "hidden"}}>
                <PatientToast isOpen={show} message={message} bgColor="#FA8072" textColor="#990F02"/>
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header style={{display: "flex"}}>
                    <HiViewList size={22}/> List Dentist
                </Card.Header>
                <Card.Body>
                    <Accordion flush>
                        {dentists.map((dentist, index) =>
                            <div style={{display: "flex"}}>
                                <div>
                                    <Button className="px-3" variant={"danger"} style={{borderRadius: 0, fontSize: 25}}
                                    onClick={()=> deleteDentist(dentist, dentist.dentistId)}>
                                        <FaTimes size={20}/>
                                    </Button>
                                </div>

                                <Accordion.Item eventKey={index} key={index} className="mb-3" style={{width: "100%"}}>
                                    <Accordion.Header
                                        style={{backgroundColor: 941663}}>Dr. {dentist.firstName}{" "}{dentist.lastName}{" ("}{dentist.specialty +") "}</Accordion.Header>
                                    <Accordion.Body>
                                        <Table bordered hover striped>
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
                                            {dentist.patients?.map((patient, index) =>
                                                <tr key={index}>
                                                    <td>{patient.firstName}</td>
                                                    <td>{patient.lastName}</td>
                                                    <td>{patient.sex}</td>
                                                    <td>{patient.age}</td>
                                                    <td>{patient.contactInfo?.mobileNumber}</td>
                                                    <td>
                                                        <button className="mx-1"
                                                                onClick={() => removePatientFromDentist(dentist, patient, dentist.dentistId, patient.patientId)}>
                                                            <HiX
                                                                color="red"/> Remove
                                                        </button>
                                                    </td>
                                                </tr>
                                            )}

                                            </tbody>
                                        </Table>
                                    </Accordion.Body>
                                </Accordion.Item>
                            </div>
                        )}
                    </Accordion>

                </Card.Body>
            </Card>
        </div>

    )
}

export default DentistList