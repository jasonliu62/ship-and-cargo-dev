import React from 'react';
import { Tab, Tabs } from 'react-bootstrap';

function StatusSelect({status, onStatusChange}) {
    return (
        <Tabs activeKey={status} onSelect={onStatusChange}>
            <Tab eventKey="0" title="实际合同"></Tab>
            <Tab eventKey="1" title="虚拟合同"></Tab>
            <Tab eventKey="2" title="即期合同"></Tab>
        </Tabs>
    );
}

export default StatusSelect;