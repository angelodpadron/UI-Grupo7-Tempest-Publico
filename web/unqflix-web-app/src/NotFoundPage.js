import React from 'react'
import { useLocation } from 'react-router-dom';

export default function NotFoundPage() {
    let location = useLocation();
  
    return (
      <div>
        <h3>
          Sorry. There's no match for <code>{location.pathname}</code>
        </h3>
      </div>
    );
  }