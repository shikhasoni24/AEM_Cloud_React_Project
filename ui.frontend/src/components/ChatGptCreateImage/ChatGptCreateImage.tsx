import React, {useState} from 'react'

type ChatGptCreateImageProps = {
    prompt: string
    numberOfImage: number
    size: string
}
export default function ChatGptCreateImage({prompt,numberOfImage,size}: ChatGptCreateImageProps) {
    const [images , setImages] = useState([]);

  function fetchData(prop: string,numberOfImage: number,size: string) {
     
        let headers = {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json',
        };
        let response = fetch("http://localhost:4502/bin/createImage?prompt=" + `${prop}`+"&n="+numberOfImage+"&size="+`${size}`, {
            method: 'get',
            headers: headers,
            'credentials': 'same-origin'
        })
        response.then(response => response.json())
            .then(json => {
                console.log(json);
                setImages(json);               
            })
    }
    
  return (
    <div id="createImageChatGpt">
        <button className="btn btn-primary" onClick={() => fetchData(`${prompt}`,numberOfImage,`${size}`)}>Click To Create Image</button>
        {images.map((image, index) => (
                            <div className="form-check">
                                <img src={image}/>
                            </div>
                        ))}
    </div>
  )
}
