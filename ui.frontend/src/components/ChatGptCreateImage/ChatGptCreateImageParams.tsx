import React,{useState} from 'react'
import ChatGptCreateImage from './ChatGptCreateImage'

export default function ChatGptCreateImageParams() {
    const [image , setImage] = useState("");
    const [numberOfImage , setNumberOfImage] = useState(0);
    const [size, setSize] = useState("");

    function handleImage(e: React.ChangeEvent<HTMLInputElement>){
        setImage(e.target.value);
    }

    function handleNumberOfImage(e: React.ChangeEvent<HTMLInputElement>){
        setNumberOfImage(Number(e.target.value));
    }

    function handleSize(e: React.ChangeEvent<HTMLInputElement>){
        setSize(e.target.value);
    }

  return (
    <div>
      <h2>Enter Details to Create Image</h2>
      <div className="input-group input-group-sm mb-3">
  <span className="input-group-text" id="inputGroup-sizing-sm">Enter Image</span>
  <input type="text" className="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value={image} onChange={handleImage} />
</div>

<div className="input-group mb-3">
  <span className="input-group-text" id="inputGroup-sizing-default">Number Of Image</span>
  <input type="text" className="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value={numberOfImage} onChange={handleNumberOfImage} />
</div>

<div className="input-group input-group-lg">
  <span className="input-group-text" id="inputGroup-sizing-lg">Size of Image(1024x1024)</span>
  <input type="text" className="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" value={size} onChange={handleSize} />
</div>
<ChatGptCreateImage prompt={image} numberOfImage={numberOfImage} size={size}/>
    </div>
  )
}
