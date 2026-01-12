import {useState, useEffect} from 'react';

export function useFetch(requestFn,deps=[]) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); 

useEffect(() => {
  let isMounted = true;

setLoading(true);

    requestFn()
      .then((res) => {
        if (isMounted) {
          setData(res.data || res); 
          setError(null);
        }
      })
      .catch((err) => {
        if (isMounted) setError(err);
      })
      .finally(() => {
        if (isMounted) setLoading(false);
      });

    return () => { isMounted = false; };
  }, deps);

  return { data, loading, error, setData };
}
