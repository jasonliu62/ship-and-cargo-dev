import React from 'react';
import { Form } from 'react-bootstrap';

function SortFieldSelect({ selectedField, onSortFieldChange, sortFields }) {
    return (<>
            <Form.Label>排序依据：</Form.Label>
            <Form.Select value={selectedField} onChange={onSortFieldChange}>
                {sortFields.map((field) => (
                    <option key={field} value={field}>
                        {field}
                    </option>
                ))}
            </Form.Select>
    </>
    );
}

export default SortFieldSelect;