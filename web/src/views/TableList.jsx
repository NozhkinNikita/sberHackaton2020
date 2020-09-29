/*!

=========================================================
* Light Bootstrap Dashboard React - v1.3.0
=========================================================

* Product Page: https://www.creative-tim.com/product/light-bootstrap-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/light-bootstrap-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";

import Card from "components/Card/Card.jsx";
import {thArray, tdArray, host} from "variables/Variables.jsx";
import {isClient} from "../helpers/helper";
import {hostSocket} from "../variables/Variables";

class TableList extends Component {

  constructor() {
    super();

    this.state = {isClient : isClient(),
                  calls :[]}


  }


  componentDidMount() {
    if (this.state.isClient) {

      const requestOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Kfmn ' + localStorage.getItem('token')
        },
      };

      fetch(host + `/client/services`, requestOptions)
          .then(response => {
            console.log(response.json()
                .then(user => {
                  console.log(user);

                }))
          });

    } else {

      this.connection = new WebSocket(hostSocket + '/socket/websocket');

let sock= this.connection;
let _this = this;

      this.connection.onopen = function () {

        var command = "SUBSCRIBE";
        var vspId = "xyi xyi xyi";

        console.log("connect");

        var send = {
          command: command,
          message: localStorage.getItem("login")
        }
        sock.send(JSON.stringify(send));


      }

      this.connection.onmessage = function (e){
        console.log(e);
        console.log(e.data.message);


        switch (e.data.command){
          case "list":{
            _this.setState({calls:e.data.message})
            break;
          }
        }
      }


    }
  }

  socketPrivet(){

  }

  render() {
    return (
      <div className="content">
        <Grid fluid>
          <Row>

            {this.state.isClient &&
                <div>
            <Col md={12}>
              <Card
                title="Striped Table with Hover"
                category="Here is a subtitle for this table"
                ctTableFullWidth
                ctTableResponsive
                content={
                  <Table striped hover>
                    <thead>
                      <tr>
                        {thArray.map((prop, key) => {
                          return <th key={key}>{prop}</th>;
                        })}
                      </tr>
                    </thead>
                    <tbody>
                      {tdArray.map((prop, key) => {
                        return (
                          <tr key={key}>
                            {prop.map((prop, key) => {
                              return <td key={key}>{prop}</td>;
                            })}
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                }
              />
            </Col>

            <Col md={12}>
              <Card
                plain
                title="Striped Table with Hover"
                category="Here is a subtitle for this table"
                ctTableFullWidth
                ctTableResponsive
                content={
                  <Table hover>
                    <thead>
                      <tr>
                        {thArray.map((prop, key) => {
                          return <th key={key}>{prop}</th>;
                        })}
                      </tr>
                    </thead>
                    <tbody>
                      {tdArray.map((prop, key) => {
                        return (
                          <tr key={key}>
                            {prop.map((prop, key) => {
                              return <td key={key}>{prop}</td>;
                            })}
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                }
              />
            </Col>
                </div>}
            {!this.state.isClient &&
            <div>

              <Col md={12}>
                <Card
                    title="Заявки"
                    category="Выбирите заявку для звонка"
                    ctTableFullWidth
                    ctTableResponsive
                    content={
                      <div><div className={"list-group"}>{this.printCalls()}</div></div>
                    }
                />
              </Col>

            </div>}
          </Row>
        </Grid>
      </div>
    );
  }

  printCalls() {
    let result = [];
    for(let i=0;i<10;i++){
      // result.push(<li onClick={()=>alert(i)} className={"list-group-item"}>i</li>);
      result.push(
          <a href="#" className="list-group-item list-group-item-action flex-column align-items-start">
            <div className="d-flex w-100 justify-content-between">
              <h5 className="mb-1">List group item heading</h5>
              <small className="text-muted">3 days ago</small>
            </div>
            <p className="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius
              blandit.</p>
            <small className="text-muted">Donec id elit non mi porta.</small>
          </a>


      );
    }

return result;
  }
}

export default TableList;
