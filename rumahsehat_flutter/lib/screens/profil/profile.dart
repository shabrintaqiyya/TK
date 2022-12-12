import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:rumahsehat_flutter/screens/login/login_screen.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;


class ProfilePage extends StatelessWidget {
  // const ProfilePage({super.key});
  ProfilePage({
    Key? key,
  }) : super(key: key);

  List<dynamic> extractedData = [];

  List<dynamic> temp = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Colors.white,
          title: Text(
            'Profile',
            style: GoogleFonts.poppins(
                color: Colors.black, fontSize: 24, fontWeight: FontWeight.bold
            ),
            // style: TextStyle(
            //     color: Colors.black, fontSize: 24, fontWeight: FontWeight.bold),
          ),
          elevation: 0,
        ),
        body: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Wrap(
                spacing: 20,
                runSpacing: 20,
                children: [
                  SizedBox(
                    height: 50,
                  ),
                  ProfilPic(),
                  Align(
                      alignment: Alignment.center,
                      child: Text(
                        'Pasien Satu',
                        // style: TextStyle(
                        //     fontSize: 20, fontWeight: FontWeight.bold),
                        textAlign: TextAlign.center,

                        style: GoogleFonts.poppins(
                            fontSize: 20, fontWeight: FontWeight.bold
                        ),
                      )),
                  Align(
                      alignment: Alignment.center,
                      child: Text(
                        'pasien1',
                        style: GoogleFonts.poppins(
                            fontSize: 20, fontWeight: FontWeight.w400
                        ),
                      )),
                  Align(
                      alignment: Alignment.center,
                      child: Text(
                        'pasien1@gmail.com',
                        style: GoogleFonts.poppins(
                            fontSize: 20, fontWeight: FontWeight.w400
                        ),
                        textAlign: TextAlign.center,
                      )),
                  Align(
                      alignment: Alignment.center,
                      child: Text(
                        'Saldo: Rp 0',
                        style: GoogleFonts.poppins(
                            fontSize: 20, fontWeight: FontWeight.w400
                        ),
                        //   style: GoogleFonts.poppins(
                        //       color: Colors.white,
                        //       fontSize: 18,
                        //       fontWeight: FontWeight.bold
                        //   )
                        // style:
                        // TextStyle(
                        //     fontSize: 20, fontWeight: FontWeight.w400),
                        textAlign: TextAlign.center,
                      )),
                ],
              ),
              const LogoutButton()
            ]));
  }

  Future<void> database() async {
    // const url = 'http://joeurnnal.herokuapp.com/message/data/';
    const url = 'http://localhost:8080/api/v1/pasien/list-pasien';
    // try {Today

    final response = await http.get(Uri.parse(url));
    // print(response.body);
    extractedData = jsonDecode(response.body);
    temp = [];
    extractedData.forEach((val) {
      // if (val['fields']['akun'] == globals.GlobalData.user) {
      //   temp.add(val);
      // }
    });

    extractedData = temp;

    //     extractedData.forEach((val){
    //   if(((val['fields']['kepada'] == globals.GlobalData.user) && (val['fields']['dari'] == widget.dari )) || ((val['fields']['dari'] == globals.GlobalData.user) && (val['fields']['kepada'] == widget.dari )) )
    //   {
    //     data.add(val);
    //     daftarKey.add(GlobalKey<FormState>());
    //     print(val);
    //   }
    // });
  }

}

class LogoutButton extends StatefulWidget {
  // const LogoutButton({super.key});
  const LogoutButton({
    Key? key,
  }) : super(key: key);

  @override
  State<LogoutButton> createState() => _LogoutButtonState();
}

class _LogoutButtonState extends State<LogoutButton> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 40, vertical: 60),
      child: // Logout Button
      MaterialButton(
        minWidth: double.infinity,
        height: 60,
        elevation: 0,
        onPressed: () async {
          SharedPreferences sp = await SharedPreferences.getInstance();
          print(sp.getString('jwtToken'));
          sp.clear();
          print(sp.getString('jwtToken'));
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => LoginScreen()));
        },
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)),
        color: Colors.red,
        child: Text(
          "Logout",
          // style: GoogleFonts.poppins(),
          style: GoogleFonts.poppins(
              color: Colors.white,
              fontSize: 18,
              fontWeight: FontWeight.bold
          )
          // style: TextStyle(
          //   fontWeight: FontWeight.bold,
          //   fontSize: 18,
          //   color: Colors.white,
          // ),
        ),
      ),
    );
  }
}

class ProfilPic extends StatelessWidget {
  // const ProfilPic({super.key});

  const ProfilPic({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(mainAxisAlignment: MainAxisAlignment.center, children: [
      Stack(
        alignment: Alignment.center,
        children: const <Widget>[
          CircleAvatar(
            radius: 65,
            backgroundColor: Color.fromARGB(255, 31, 139, 204),
            child: CircleAvatar(
              radius: 60,
              backgroundImage: NetworkImage(
                  'https://media.discordapp.net/attachments/736569371727626300/1051093795363627098/dummy450x450.png'),
              backgroundColor: Colors.grey,
            ),
          ),
        ],
      ),
    ]);
  }
}
