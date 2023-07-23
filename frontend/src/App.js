import './App.css';
import Slider from '@mui/material/Slider'
import { useState } from 'react';

function App() {
  const [rating, setRating] = useState([0, 10])
  const handleChange = (event, newValue) => {
    // if (newValue > rating[0]) {
    //   setRating([newValue, rating[1]])
    // }
    // else {
    //   setRating([rating[0], newValue])
    // }
    console.log(event, newValue)
    
  };
  return (
    <div className="App">
      <h1 className='header'>Choose parameters for your movie:</h1>
      <div className='rating-container'>
      <h3>Choose the rating of the movie:</h3>
        <Slider
        color='secondary'
        defaultValue={5}
        valueLabelDisplay="auto"
        step={1}
        marks
        value={rating}
        onChange={handleChange}
        min={0}
        max={10}
        disableSwap
      /></div>
    </div>
  );
}

export default App;
