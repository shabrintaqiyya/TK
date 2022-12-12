// import 'dart:html';

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../../login-logout/Components/background.dart';
import '../../login-logout/Components/rounded_button.dart';
import '../../login-logout/Components/rounded_input_field.dart';
import '../../login-logout/Components/rounded_password_field.dart';
import '../../login-logout/Components/title_form.dart';
import '../home.dart';
import '../pasien/registrasi_pasien.dart';
// import 'package:rumahsehat_flutter/screens/login-logout/Screens/Login/login_screen.dart';

// void main() {
//   runApp(Body());
// }

class LoginScreen extends StatelessWidget {
  // const MyApp({Key? key}) : super(key: key);

  final _loginFormKey = GlobalKey<FormState>();
  String username = "";
  String password = "";

  LoginScreen({
    Key? key,
  }) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    RoundedInputField temp1 = RoundedInputField(text: 'Username', inputType: TextInputType.name, input: username);
    RoundedPasswordField temp2 = RoundedPasswordField(text: 'Password', input: password);

    return Scaffold (
      // appBar: AppBar(
      //   centerTitle: true,
      //   title:
      //   Text(
      //     'Selamat Datang di Rumah Sehat',
      //     textAlign: TextAlign.center,
      //     style: GoogleFonts.poppins(),
      //   ),
      // ),
        // value: SystemUiOverlayStyle.light,
        body: GestureDetector(
            child: Stack(
              children: <Widget>[
                Form(
                  key: _loginFormKey,
                  child: Background(
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          // Text(
                          //   'Selamat Datang di Rumah Sehat',
                          //   textAlign: TextAlign.center,
                          //   style: GoogleFonts.poppins(),
                          // ),
                          // Text('Login Form', style: GoogleFonts.poppins()),
                          // Center(child: Text('Selamat Datang di Rumah Sehat')),
                          TitleForm(text: 'Selamat Datang di Rumah Sehat'),
                          SizedBox(height: 50,),
                          temp1,
                          SizedBox(height: 20,),
                          temp2,
                          Padding(padding: const EdgeInsets.only(top: 20)),
                          RoundedButton(
                            text: 'LOGIN',
                            press: () async {
                              if (_loginFormKey.currentState!.validate()) {
                                final response = await login(temp1.input, temp2.input);
                                if (response) {
                                  ScaffoldMessenger.of(context)
                                      .showSnackBar(const SnackBar(
                                    content: Text("Akun telah berhasil masuk!"),
                                  ));
                                  // globals.GlobalData.user = temp1.input;
                                  // Navigator.pushReplacementNamed(context, HomePage.routeName);
                                  Navigator.of(context).push(
                                      MaterialPageRoute(builder: (context) => HomePage())
                                  );
                                } else {
                                  ScaffoldMessenger.of(context)
                                      .showSnackBar(const SnackBar(
                                    content:
                                    Text("An error occured, please try again."),
                                  ));
                                }
                              }

                              else {
                                ScaffoldMessenger.of(context)
                                    .showSnackBar(const SnackBar(
                                  content: Text("Login tidak valid!"),
                                ));
                                // Navigator.pushReplacementNamed(context, LandingScreen.routeName);
                              }
                            },
                          ),

                          RoundedButton(text: 'REGISTRASI PASIEN', press: () {
                            Navigator.of(context).push(
                                MaterialPageRoute(builder: (context) => RegisScreen())
                            );
                          })
                        ]
                    ),
                  ),
                ),
              ],
            )
        )
    );
  }
}

addSharedPref(jwtToken) async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  sp.setString('jwtToken', jwtToken);
}

Future<bool> login(String username, String password) async {
  // final Dio dio = new Dio();
  // var url = 'https://api.genderize.io/?name=' + username;
  // Map data = {'username': username, 'password': password};
  // var body = json.encode(data);
  // var response = await http.get(Uri.parse(url));
  // print(response.statusCode);
  // print(response.body);

  // print(umur);
  // int umur2 = int.parse(umur);

  var body = jsonEncode(<String, dynamic>{
    "username": username,
    "password": password
  });

  var response = await http.post(
      Uri.parse('http://apap-125.cs.ui.ac.id/authenticate'),
      // Uri.parse('http://localhost:8080/authenticate'),
      headers: <String, String>{"Content-Type": "application/json; charset=UTF-8",
        "Accept": "application/json"},
      body:  body
  );

  print(response.statusCode);
  // print(json.decode(response.body)['jwttoken']);

  String token = json.decode(response.body)['jwttoken'];
  print(token);
  addSharedPref(token);
  print(response.body);
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return true;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception
    return false;
  }
}
