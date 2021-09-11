import 'package:flutter/material.dart';
import 'package:skinclik/ui/login.dart';
import 'package:skinclik/ui/signup.dart';

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: HomePage(),
  ));
}

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  Widget _buildSignupandLoginPage(BuildContext context) {
    return Column(
      children: <Widget>[
        MaterialButton(
            minWidth: double.infinity,
            height: 60,
            onPressed: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LoginPage()));
            },
            shape: RoundedRectangleBorder(
                side: BorderSide(color: Colors.black),
                borderRadius: BorderRadius.circular(50)),
            child: Text(
              'Login',
              style: TextStyle(fontWeight: FontWeight.w600, fontSize: 18),
            )),
        SizedBox(height: 10),
        MaterialButton(
            minWidth: double.infinity,
            height: 60,
            onPressed: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => SignupPage()));
            },
            color: Color(0xff0095FF),
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)),
            child: Text(
              'SignUp',
              style: TextStyle(fontWeight: FontWeight.w600, fontSize: 18),
            ))
      ],
    );
  }

  Widget _buildWelcomeImage(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height / 3,
      decoration: BoxDecoration(
          image: DecorationImage(image: AssetImage("asset/welcome.png"))),
    );
  }

  Widget _buildWelcomeText() {
    return Column(
      children: [
        Text("Welcome",
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 30)),
        SizedBox(height: 20),
        Text("Skinclik",
            textAlign: TextAlign.center,
            style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 15,
                color: Colors.grey[700]))
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      width: double.infinity,
      height: MediaQuery.of(context).size.height,
      padding: EdgeInsets.symmetric(horizontal: 30, vertical: 50),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          _buildWelcomeText(),
          _buildWelcomeImage(context),
          _buildSignupandLoginPage(context)
        ],
      ),
    ));
  }
}
