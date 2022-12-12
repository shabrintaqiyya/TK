
// import 'dart:html';

import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;
import '../../login-logout/Components/background.dart';
import '../../login-logout/Components/rounded_button.dart';
import '../../login-logout/Components/rounded_input_field.dart';
import '../../login-logout/Components/rounded_password_field.dart';
import '../../login-logout/Components/title_form.dart';
import '../login/login_screen.dart';
// import 'package:rumahsehat_flutter/screens/login-logout/Screens/Login/login_screen.dart';

// void main() {
//   runApp(Body());
// }

class RegisScreen extends StatelessWidget {
  // const MyApp({Key? key}) : super(key: key);

  final _loginFormKey = GlobalKey<FormState>();

  // String nama = "";
  // String email = "";
  // String username = "";
  // String password = "";
  // String umur = "";

  final TextEditingController _nama = TextEditingController();
  final TextEditingController _email = TextEditingController();
  final TextEditingController _username = TextEditingController();
  final TextEditingController _password = TextEditingController();
  final TextEditingController _umur = TextEditingController();

  RegisScreen({
    Key? key,
  }) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    RoundedInputField namaInput = RoundedInputField(text: 'Nama', inputType: TextInputType.name, input: _nama);
    RoundedInputField emailInput = RoundedInputField(text: 'Email', inputType: TextInputType.name, input: _email);
    RoundedInputField usernameInput = RoundedInputField(text: 'Username', inputType: TextInputType.name, input: _username);
    RoundedPasswordField passwordInput = RoundedPasswordField(text: 'Password', input: _password);
    RoundedInputField umurInput = RoundedInputField(text: 'Umur', inputType: TextInputType.number, input: _umur);

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
                          TitleForm(text: 'Registrasi Pasien'),
                          SizedBox(height: 20,),
                          namaInput,
                          SizedBox(height: 20,),
                          emailInput,
                          SizedBox(height: 20,),
                          usernameInput,
                          SizedBox(height: 20,),
                          passwordInput,
                          SizedBox(height: 20,),
                          umurInput,
                          // Padding(padding: const EdgeInsets.only(top: 20)),
                          // RoundedButton(
                          //   text: 'LOGIN',
                          //   press: () async {
                          //     if (_loginFormKey.currentState!.validate()) {
                          //       final response = await login(temp1.input, temp2.input);
                          //       if (response) {
                          //         ScaffoldMessenger.of(context)
                          //             .showSnackBar(const SnackBar(
                          //           content: Text("Akun telah berhasil masuk!"),
                          //         ));
                          //         // globals.GlobalData.user = temp1.input;
                          //         // Navigator.pushReplacementNamed(context, HomePage.routeName);
                          //       } else {
                          //         ScaffoldMessenger.of(context)
                          //             .showSnackBar(const SnackBar(
                          //           content:
                          //           Text("An error occured, please try again."),
                          //         ));
                          //       }
                          //     }
                          //
                          //     else {
                          //       ScaffoldMessenger.of(context)
                          //           .showSnackBar(const SnackBar(
                          //         content: Text("Login tidak valid!"),
                          //       ));
                          //       // Navigator.pushReplacementNamed(context, LandingScreen.routeName);
                          //     }
                          //   },
                          // ),
                          //
                          RoundedButton(text: 'REGISTER', press: () async {
                            var response = await register(namaInput.input, emailInput.input, usernameInput.input, passwordInput.input, int.parse(umurInput.input));
                            if(response){
                              ScaffoldMessenger.of(context)
                                  .showSnackBar(const SnackBar(
                                content: Text("Registrasi berhasil!"),
                              ));
                              Navigator.of(context).push(
                                  MaterialPageRoute(builder: (context) => LoginScreen())
                              );
                              // _nama.clear();
                              // _email.clear();
                              // _username.clear();
                              // _password.clear();
                              // _umur.clear();

                            }
                          }),
                          RoundedButton(text: 'CANCEL', press: () {
                            Navigator.of(context).push(
                                MaterialPageRoute(builder: (context) => LoginScreen())
                            );
                          }),
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

Future<bool> register(String nama, String email, String username, String password, int umur) async {
  // final Dio dio = new Dio();
  // var url = 'https://api.genderize.io/?name=' + username;
  // Map data = {'username': username, 'password': password};
  // var body = json.encode(data);
  // var response = await http.get(Uri.parse(url));
  // print(response.statusCode);
  // print(response.body);

  print(umur);
  // int umur2 = int.parse(umur);

  var body = jsonEncode(<String, dynamic>{
    "nama": nama,
    "email": email,
    "username": username,
    "password": password,
    "umur": umur,
    "role": "Pasien",
    "isSso": false,
    "saldo": 0
  });

  var response = await http.post(
      Uri.parse('http://localhost:8080/api/v1/pasien/add'),
      headers: <String, String>{"Content-Type": "application/json; charset=UTF-8",
        "Accept": "application/json"},
      body:  body
  );

  print(response.statusCode);
  print(response.body);

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return true;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    // BuildContext context;
    // ScaffoldMessenger.of(context).showSnackBar(
    //   const SnackBar(content: Text('Processing Data')),
    // );
    return false;
  }
}



// class MyHomePage extends StatefulWidget {
//   const MyHomePage({Key? key, required this.title}) : super(key: key);
//
//   // This widget is the home page of your application. It is stateful, meaning
//   // that it has a State object (defined below) that contains fields that affect
//   // how it lo
//   oks.
//
//   // This class is the configuration for the state. It holds the values (in this
//   // case the title) provided by the parent (in this case the App widget) and
//   // used by the build method of the State. Fields in a Widget subclass are
//   // always marked "final".
//
//   final String title;
//
//   @override
//   State<MyHomePage> createState() => _MyHomePageState();
// }

// class _MyHomePageState extends State<MyHomePage> {
//   int _counter = 0;
//
//   void _incrementCounter() {
//     setState(() {
//       // This call to setState tells the Flutter framework that something has
//       // changed in this State, which causes it to rerun the build method below
//       // so that the display can reflect the updated values. If we changed
//       // _counter without calling setState(), then the build method would not be
//       // called again, and so nothing would appear to happen.
//       _counter++;
//     });
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     // This method is rerun every time setState is called, for instance as done
//     // by the _incrementCounter method above.
//     //
//     // The Flutter framework has been optimized to make rerunning build methods
//     // fast, so that you can just rebuild anything that needs updating rather
//     // than having to individually change instances of widgets.
//     return Scaffold(
//       appBar: AppBar(
//         // Here we take the value from the MyHomePage object that was created by
//         // the App.build method, and use it to set our appbar title.
//         title: Text(widget.title),
//       ),
//       body: Center(
//         // Center is a layout widget. It takes a single child and positions it
//         // in the middle of the parent.
//         child: Column(
//           // Column is also a layout widget. It takes a list of children and
//           // arranges them vertically. By default, it sizes itself to fit its
//           // children horizontally, and tries to be as tall as its parent.
//           //
//           // Invoke "debug painting" (press "p" in the console, choose the
//           // "Toggle Debug Paint" action from the Flutter Inspector in Android
//           // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
//           // to see the wireframe for each widget.
//           //
//           // Column has various properties to control how it sizes itself and
//           // how it positions its children. Here we use mainAxisAlignment to
//           // center the children vertically; the main axis here is the vertical
//           // axis because Columns are vertical (the cross axis would be
//           // horizontal).
//           mainAxisAlignment: MainAxisAlignment.center,
//           children: <Widget>[
//             const Text(
//               'You have pushed the button this many times:',
//             ),
//             Text(
//               '$_counter',
//               style: Theme.of(context).textTheme.headline4,
//             ),
//           ],
//         ),
//       ),
//       floatingActionButton: FloatingActionButton(
//         onPressed: _incrementCounter,
//         tooltip: 'Increment',
//         child: const Icon(Icons.add),
//       ), // This trailing comma makes auto-formatting nicer for build methods.
//     );
//   }
// }
