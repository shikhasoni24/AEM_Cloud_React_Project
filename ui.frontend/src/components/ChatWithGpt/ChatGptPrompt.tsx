import React,{useState} from 'react'
import ChatGptResponse from './ChatGptResponse';

export default function ChatGptPrompt() {
 const [val,setVal] = useState("");

 const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setVal(e.target.value);
 }

  return (
    <div className="form-group">
      <label>Enter question</label>
      <input type="text" className="form-control" value={val} onChange={handleChange}/>
      <ChatGptResponse prompt={val}/>
    </div>
  )
}
