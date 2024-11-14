import React from 'react';
import { Modal, Button } from 'react-bootstrap';

function ModalTemplate({ isOpen, onClose, children, title }) {
    return (
        <Modal show={isOpen} onHide={onClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>{title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{children}</Modal.Body>
        </Modal>
    );
}

export default ModalTemplate;
