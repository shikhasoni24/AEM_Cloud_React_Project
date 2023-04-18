/* global google */
import { useState, useEffect } from 'react'
import jwt_decode from 'jwt-decode';

function GoogleAuth() {
  const [isSignedIn, setIsSignedIn] = useState(false);
  const [userInfo, setUserInfo] = useState(null)

  const onOneTapSignedIn = response => {
    setIsSignedIn(true)
    const decodedToken = jwt_decode(response.credential)
    setUserInfo({...decodedToken})
  }

  const initializeGSI = () => {
    google.accounts.id.initialize({
      client_id: '968838385425-i8hmjd0g8ghd9bbn72vb3acfstaurdh9.apps.googleusercontent.com',
      cancel_on_tap_outside: false,
      callback: onOneTapSignedIn
    });
    google.accounts.id.prompt((notification) => {
      if (notification.isNotDisplayed()) {
        console.log(notification.getNotDisplayedReason())
      } else if (notification.isSkippedMoment()) {
        document.cookie = "g_state; max-age=0";

// Specifying path and domain while deleting cookie
      document.cookie = "g_state=; path=/;  max-age=0";
        console.log(notification.getSkippedReason())
      } else if(notification.isDismissedMoment()) {
        console.log(notification.getDismissedReason())
      }
    });
  }

  const signout = () => {
    // refresh the page
    window.location.reload();
  }

  useEffect(() => {
    const el = document.createElement('script')
    el.setAttribute('src', 'https://accounts.google.com/gsi/client')
    el.onload = () => initializeGSI();
    document.querySelector('body').appendChild(el)
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <img src="" className="App-logo" alt="logo" />
        { isSignedIn && userInfo ?
          <div>
            Hello {userInfo.name} ({userInfo.email})
            <div className="g_id_signout" onClick={() => signout()}>Sign Out</div>
          </div> :
          <div>You are not signed in</div>
        }
      </header>
    </div>
  );
}

export default GoogleAuth;