import React from "react";
import {useHistory} from "react-router-dom";
import bookTerm from "../BookTerm/BookTerm";
 const BookEdit = (props) =>{
     const history=useHistory();
     const [formData, updateFormData]
         = React.useState({
         name:"",
         category:"",
         author: null,
         availableCopies:1
     })
     const handleChange = (e) => {
         updateFormData({
             ...formData,
             [e.target.name]: e.target.value.trim()
         })
     }

     const onFormSubmit = (e) => {
         e.preventDefault();
         const name = formData.name !== "" ? formData.name : props.book.name;
         const category = formData.category !== "" ? formData.category : props.book.category;
         const author = formData.author !== "" ? formData.author : props.book.author;
         const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

         props.onEditBook(props.book.id,name, category, author, availableCopies);
         history.push("/books");
     }
     return(
         <div className="row mt-5">
             <div className="col-md-5">
                 <form onSubmit={onFormSubmit}>
                     <div className="form-group">
                         <label htmlFor="name">Book name</label>
                         <input type="text"
                                className="form-control"
                                id="name"
                                name="name"
                                required
                                placeholder={props.book.name}
                                defaultValue={props.book.name}
                                onChange={handleChange}
                         />
                     </div>
                     <div className="form-group">
                         <label htmlFor="category">Category</label>
                         <input type="text"
                                className="form-control"
                                id="category"
                                name="category"
                                placeholder="category"
                                required
                                onChange={handleChange}
                         />
                     </div>

                     <div className="form-group">
                         <label htmlFor="availableCopies">AvailableCopies</label>
                         <input type="text"
                                className="form-control"
                                id="availableCopies"
                                name="availableCopies"
                                placeholder="availableCopies"
                                required
                                onChange={handleChange}
                         />
                     </div>

                     <div className="form-group">
                         <label>Author</label>
                         <select name="author" className="form-control" onChange={handleChange}>
                             {props.authors.map((term) =>
                                 <option value={term.id}>{term.name}</option>
                             )}
                         </select>
                     </div>

                     <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                 </form>
             </div>
         </div>

     )
 }
 export default BookEdit;