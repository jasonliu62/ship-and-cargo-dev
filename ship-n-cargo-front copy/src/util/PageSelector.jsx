import React from 'react';
import { Pagination } from 'react-bootstrap';

function PageSelector({ currentPage, totalPages, onPageChange }) {
    const items = [];

    for (let number = 1; number <= totalPages; number++) {
        items.push(
            <Pagination.Item key={number} active={number === currentPage} onClick={() => onPageChange(number)}>
                {number}
            </Pagination.Item>,
        );
    }

    return (
        totalPages === 0 ? <div>记录为空</div> :
            <div className="page-selector">
                <Pagination>{items}</Pagination>
            </div>
    );
}

export default PageSelector;