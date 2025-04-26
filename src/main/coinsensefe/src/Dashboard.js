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

function getTodayDateString() {
  return new Date().toISOString().split('T')[0]; // "2025-04-26"
}

function ExpenseTable({name}) {

  const [date, setDate] = useState(getTodayDateString());
  console.log('Name :', name);
  const username = {name};
  console.log('Names :', username);
  const [rows, setRows] = useState([
    { dateOfExpense: '', expenseType: '', amount: '' },
  ]);
  const [expense, setExpense] = useState({dateOfExpense:'', expenseType:'', amount:''});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitMessage, setSubmitMessage] = useState('');

  // Add row
  const addRow = () => {
    console.log('rows before : ', rows);
    console.log('expense row values : ', expense);
    if(rows[0].amount===''){
        setRows([expense])
    }
    else{
        setRows([...rows, expense]);
    }
  };

  // Handle input changes
  const handleChange = (field, value) => {
    const newExpense = expense;
    if (field === 'amount') {
      const numericValue = parseFloat(value);
      newExpense[field] = !isNaN(numericValue) ? Math.max(0, numericValue).toString() : '';
    } else {
      newExpense[field] = value;
    }
    setExpense(newExpense);
    console.log('new value for new row being added : ', expense);
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

  const handleRowSubmit = (e) => {
  e.preventDefault();
    addRow();
    console.log('rows after : ', rows);
  }

  const handleDateChange = (value) => {
    setDate(value)
    handleChange('dateOfExpense', value)
  }

  return (
    <div style={{ padding: '24px', fontFamily: 'Segoe UI, Arial, sans-serif', background: '#f6f8fa', minHeight: '100vh' }}>
      <h1 style={{ color: '#2d3748', marginBottom: 0 }}>Coinsense</h1>
      <h3 style={{ color: '#4fd1c5', marginTop: 4, marginBottom: 24 }}>Welcome {name}!!</h3>
      <form onSubmit={handleRowSubmit}>
        <table style={{ borderCollapse: 'collapse', width: '100%', maxWidth: 700, background: '#fff', boxShadow: '0 2px 8px #e2e8f0', borderRadius: 8, overflow: 'hidden' }}>
          <thead>
            <tr>
              {Object.keys(expense).map((key) => (
                <th key={key}>{key}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            <tr>
              <td key='dateOfExpense'>
                <input
                  name='dateOfExpense'
                  type = "date"
                  value={date}
                  onChange={e => handleDateChange(e.target.value)}
                />
              </td>
            <td key='expenseType'>
              <input
                  name='expenseType'
                  type = "text"
                  onChange={e => handleChange('expenseType', e.target.value)}
              />
            </td>
            <td key='amount'>
                <input
                  name='amount'
                  type = "number"
                  max="10000000"
                  onChange={e => handleChange('amount', e.target.value)}
                />
            </td>
          </tr>
          </tbody>
        </table>
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
          {isSubmitting ? 'Submitting...' : 'Submit New Expense'}
        </button>
        {submitMessage && (
          <div style={{ marginTop: 16, color: submitMessage.includes('success') ? 'green' : 'red' }}>
            {submitMessage}
          </div>
        )}
      </form>
      <table style={{ borderCollapse: 'collapse', width: '100%', maxWidth: 700, background: '#fff', boxShadow: '0 2px 8px #e2e8f0', borderRadius: 8, overflow: 'hidden' }}>
                <thead>
                  <tr style={{ background: '#4fd1c5', color: '#fff' }}>
                    <th style={{ padding: '12px' }}>S.No.</th>
                    <th style={{ padding: '12px' }}>DateOfExpense</th>
                    <th style={{ padding: '12px' }}>ExpenseType</th>
                    <th style={{ padding: '12px' }}>Amount</th>
                  </tr>
                </thead>
                <tbody>
                  {rows.map((row, index) => (
                    <tr key={index} style={{ background: row.amount > 1000 ? '#964BOO' : index % 2 === 0 ? '#FFFF000' : '#e6fffa' }}>
                      <td style={{ padding: '10px' }}>
                        <input
                          type="text"
                          disabled
                          value={index}
                          style={{ width: '20px', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}
                        />
                      </td>
                      <td style={{ padding: '10px' }}>
                        <input
                          type="date"
                          disabled
                          value={row.dateOfExpense}
                          style={{ width: '100%', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}
                        />
                      </td>
                      <td style={{ padding: '10px' }}>
                        <input
                          name='expenseType'
                          value = {row.expenseType}
                          disabled
                        />
                      </td>
                      <td style={{ padding: '10px' }}>
                        <input
                          type="number"
                          disabled
                          min="0"
                          value={row.amount}
                          onChange={e => handleChange('amount', e.target.value)}
                          style={{ width: '100%', border: '1px solid #cbd5e1', borderRadius: 4, padding: '6px' }}
                        />
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
    </div>
  );
}

export default ExpenseTable;
