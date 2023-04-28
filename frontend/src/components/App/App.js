import './App.css';
import React,{Component} from "react";
import {BrowserRouter as Router, Route,Redirect} from 'react-router-dom'
import LibraryService from "../../repository/LibraryRepository";
import Books from "../Books/books.js";
import Authors from "../Authors/Authors";
import Countries from "../Countries/Countries";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/BookEdit";
class App extends Component{
  constructor(props) {
    super(props);
    this.state={
      books:[],
      authors:[],
      countries:[],
      selectedBook: {}
    }
  }

  render() {
    return(
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/authors"} exact render={()=> <Authors authors={this.state.authors}/>}/>
              <Route path={"/countries"} exact render={()=> <Countries countries={this.state.countries}/>}/>
              <Route path={"/books/add"} exact render={()=> <BookAdd authors={this.state.authors} onAddBook={this.addBook}/>}/>
              <Route path={"/books/edit/:id"} exact render={()=> <BookEdit authors={this.state.authors} onEditBook={this.editBook} book={this.state.selectedBook}/>}/>
              <Route path={"/books"} exact render={()=> <Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook}/>}/>
            </div>
          </main>
        </Router>
    );
  }
  loadBooks = () =>{
    LibraryService.fetchBooks().then((data)=>{
      this.setState({
        books:data.data
      })
    });
  }
  loadCountries = ()=>{
    LibraryService.fetchCountries().then((data)=>{
      this.setState({
        countries:data.data
      })
    });
  }
  loadAuthors = ()=>{
    LibraryService.fetchAuthors().then((data)=>{
      this.setState({
        authors:data.data
      })
    });
  }
  deleteBook = (id)=>{
    LibraryService.deleteBook(id).then(()=>{
      this.loadBooks();
    });
  }
  addBook =(name,category,author,availableCopies)=>{
    LibraryService.addBook(name,category,author,availableCopies)
        .then(()=>{
          this.loadBooks();
        });
  }
  getBook =(id)=>{
    LibraryService.getBook().then((data)=>{
      this.setState({
        selectedProduct:data.data
      })
    })
  }
  editBook = (id,name,category,author,availableCopies)=>{
    LibraryService.editBook(id,name,category,author,availableCopies)
        .then(()=>{
      this.loadBooks();
    })
  }
  componentDidMount() {
    this.loadCountries();
    this.loadAuthors();
    this.loadBooks();
  }
}

export default App;
