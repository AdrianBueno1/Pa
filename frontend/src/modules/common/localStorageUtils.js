export const addObject = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
}

export const getObject = (key) => {
    const value = localStorage.getItem(key);
    
    if (!value) {
        return null;
    }else{
        return JSON.parse(value);
    }
}

export const removeKey = (key) => {
    localStorage.removeItem(key);
}