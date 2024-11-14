import React from 'react';
import { Form } from 'react-bootstrap';

function SortDirectionSelect({ selectedDirection, onSortDirectionChange }) {
    return (<>
            <Form.Label>排序方向：</Form.Label>
            <Form.Select value={selectedDirection} onChange={onSortDirectionChange}>
                <option value="asc">升序</option>
                <option value="desc">降序</option>
            </Form.Select>
    </>
    );
}

export default SortDirectionSelect;