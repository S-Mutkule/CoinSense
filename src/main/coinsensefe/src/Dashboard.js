import React, { useState } from 'react';

// Define your expense types
const ExpenseTypeEnum = {
  FOOD: 'Food',
  TRANSPORT: 'Transport',
  ENTERTAINMENT: 'Entertainment',
  HEALTHCARE: 'Healthcare',
  UTILITIES: 'Utilities',
  OTHER: 'Other',
};


function ExpenseTable({name}) {
  console.log('Name :', name);
  const username = {name};
  console.log('Names :', username);
  const [rows, setRows] = useState([
    { dateOfExpense: '', expenseType: '', amount: '' },
  ]);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitMessage, setSubmitMessage] = useState('');

  // Add row
  const addRow = () => {
    setRows([...rows, { dateOfExpense: '', expenseType: '', amount: '' }]);
  };

  // Delete row
  const handleDeleteRow = (indexToDelete) => {
    setRows(rows.filter((_, idx) => idx !== indexToDelete));
  };

  // Handle input changes
  const handleChange = (index, field, value) => {
    const newRows = [...rows];
    if (field === 'amount') {
      const numericValue = parseFloat(value);
      newRows[index][field] = !isNaN(numericValue) ? Math.max(0, numericValue).toString() : '';
    } else {
      newRows[index][field] = value;
    }
    setRows(newRows);
  };

  // Submit handler
  const handleSubmit = async (event) => {
    event.preventDefault();
    setIsSubmitting(true);
    setSubmitMessage('');
    try {
      const response = await fetch('/expenses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ expenses: rows }),
      });
      if (response.ok) {
        setSubmitMessage('Expenses submitted successfully!');
      } else {
        setSubmitMessage('Error submitting expenses.');
      }
    } catch (error) {
      setSubmitMessage('Network error submitting expenses.');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div style={{ padding: '24px', fontFamily: 'Segoe UI, Arial, sans-serif', background: '#f6f8fa', minHeight: '100vh' }}>
      <h1 style={{ color: '#2d3748', marginBottom: 0 }}>Coinsense</h1>
      <h3 style={{ color: '#4fd1c5', marginTop: 4, marginBottom: 24 }}>Welcome {name}!!</h3>
      <form onSubmit={handleSubmit}>
        <table style={{ borderCollapse: 'collapse', width: '100%', maxWidth: 700, background: '#fff', boxShadow: '0 2px 8px #e2e8f0', borderRadius: 8, overflow: 'hidden' }}>
          <thead>
            <tr style={{ background: '#4fd1c5', color: '#fff' }}>
              <th style={{ padding: '12px' }}>DateOfExpense</th>
              <th style={{ padding: '12px' }}>ExpenseType</th>
              <th style={{ padding: '12px' }}>Amount</th>
              <th style={{ padding: '12px' }}></th>
            </tr>
          </thead>
          <tbody>
            {rows.map((row, index) => (
              <tr key={index} style={{ background: index % 2 === 0 ? '#f7fafc' : '#e6fffa' }}>
                <td style={{ padding: '10px' }}>
                  <input
                    type="date"
                    value={row.dateOfExpense}
                    onChange={e => handleChange(index, 'dateOfExpense', e.target.value)}
                    style={{ width: '100%', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}
                  />
                </td>
                <td style={{ padding: '10px' }}>
                  <select
                    value={row.expenseType}
                    onChange={e => handleChange(index, 'expenseType', e.target.value)}
                    style={{ width: '100%', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}
                  >
                    <option value="">Select Type</option>
                    {Object.values(ExpenseTypeEnum).map(type => (
                      <option key={type} value={type}>{type}</option>
                    ))}
                  </select>
                </td>
                <td style={{ padding: '10px' }}>
                  <input
                    type="number"
                    min="0"
                    value={row.amount}
                    onChange={e => handleChange(index, 'amount', e.target.value)}
                    style={{ width: '100%', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}

                  />
                </td>
                <td style={{ padding: '10px', textAlign: 'center' }}>
                  <button
                    type="button"
                    onClick={() => handleDeleteRow(index)}
                    style={{
                      background: '#e53e3e',
                      color: '#fff',
                      border: 'none',
                      borderRadius: 4,
                      padding: '6px 12px',
                      cursor: 'pointer',
                      fontWeight: 600,
                      transition: 'background 0.2s',
                    }}
                    title="Delete row"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <button
          type="button"
          onClick={addRow}
          style={{
            marginTop: 20,
            background: '#4fd1c5',
            color: '#fff',
            border: 'none',
            borderRadius: 4,
            padding: '10px 24px',
            fontSize: 16,
            fontWeight: 600,
            cursor: 'pointer',
            boxShadow: '0 1px 3px #e2e8f0',
            transition: 'background 0.2s',
            marginRight: 16,
          }}
        >
          Add New Row
        </button>
        <button
          type="submit"
          disabled={isSubmitting}
          style={{
            marginTop: 20,
            background: '#2d3748',
            color: '#fff',
            border: 'none',
            borderRadius: 4,
            padding: '10px 24px',
            fontSize: 16,
            fontWeight: 600,
            cursor: isSubmitting ? 'not-allowed' : 'pointer',
            boxShadow: '0 1px 3px #e2e8f0',
            transition: 'background 0.2s',
          }}
        >
          {isSubmitting ? 'Submitting...' : 'Submit'}
        </button>
        {submitMessage && (
          <div style={{ marginTop: 16, color: submitMessage.includes('success') ? 'green' : 'red' }}>
            {submitMessage}
          </div>
        )}
      </form>
    </div>
  );
}

export default ExpenseTable;
