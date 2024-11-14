import React from 'react';
import {Form, FormLabel} from 'react-bootstrap';

function PageSizeSelect({pageSize, onPageSizeChange}) {
    return (<>
            <FormLabel>每页显示数量：</FormLabel>
            <Form.Select onChange={onPageSizeChange} value={pageSize}>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="40">40</option>
            </Form.Select>
    </>
    );
}

export default PageSizeSelect;