import {Toast, ToastContainer} from "react-bootstrap";

const PatientToast = (props) => {
    return (
        <ToastContainer className="p-3" position="bottom-center">
            <Toast show={props.isOpen} className="text-center"
                   style={{backgroundColor: props.bgColor, color: props.textColor, fontWeight: "bold"}}>
                <Toast.Body>{props.message}</Toast.Body>
            </Toast>
        </ToastContainer>
    )
}

export default PatientToast