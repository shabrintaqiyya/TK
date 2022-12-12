import 'dart:js';

import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
// import 'package:rumah_sehat_flutter/page/create_appointment.dart';
import 'package:rumahsehat_flutter/screens/home.dart';
import 'package:rumahsehat_flutter/screens/profil/profile.dart';
// import 'package:rumah_sehat_flutter/page/profile_page.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int index = 0;
  final screens = [
    Container(
        // double width = MediaQuery.of(context).size.width,
        margin: EdgeInsets.symmetric(vertical: 100.0, horizontal: 100.0),
        alignment: Alignment.topCenter,
        child: Column(children: <Widget>[
          Text("Selamat Datang di Rumah Sehat\n",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 25,
              ),
              textAlign: TextAlign.center),
          // SizedBox(height: 16),
          // Text("Klik tombol dibawah ini untuk melaporkan keluhanmu!",
          //     style: TextStyle(
          //       fontSize: 20,
          //     ),
          //     textAlign: TextAlign.center),
          // GestureDetector(
          //   onTap: (){
          //     Navigator.push(context, MaterialPageRoute(builder: (context) => HomePage()));
          //   },
          //   child: const L istTile(
          //     leading: Icon(
          //       Icons.home,
          //       size: 26,
          //       color: Colors.black,
          //     ),
          //     title: Text(
          //       "Beranda",
          //       style:
          //       TextStyle(fontSize: 15, fontWeight: FontWeight.w500),
          //     ),
          //   ),
          // )

          Center(
            child: TextButton(
              child: Text(
                "Lihat Appointment",
              ),
              onPressed: () {
                // Navigator.push(
                //   context,
                //   MaterialPageRoute(
                //       builder: (context) => ListAppointmentPage()),
                // );
              },
            ),
          ),
        ])),
    // 1
    const Center(
      child: Text(
        'Appointment',
        style: TextStyle(fontSize: 72),
      ),
    ),
    //2
    const Center(
      child: Text(
        'Obat',
        style: TextStyle(fontSize: 72),
      ),
    ),
    // 3
    const Center(
      child: Text(
        'Resep',
        style: TextStyle(fontSize: 72),
      ),
    ),
    // 4
    const Center(
      child: Text(
        'Payment',
        style: TextStyle(fontSize: 72),
      ),
    ),
    // 5
    // const ProfilePage(),
    ProfilePage(),
    // const Center(
    //   child: Text(
    //     'Profile',
    //     style: TextStyle(fontSize: 72),
    //   ),
    // ),
  ];

  @override
  Widget build(BuildContext context) => Scaffold(
        body: screens[index],
        backgroundColor: Colors.white,
        bottomNavigationBar: BottomNavigationBar(
            currentIndex: index,
            type: BottomNavigationBarType.fixed,
            backgroundColor: Colors.white,
            selectedItemColor: Color.fromARGB(255, 31, 139, 204),
            unselectedItemColor: Colors.grey,
            selectedFontSize: 14,
            onTap: (int index) {
              setState(() {
                this.index = index;
              });
            },
            // data: NavigationBarThemeData(
            //     indicatorColor: Color.fromARGB(255, 163, 255, 246),
            //     labelTextStyle: MaterialStateProperty.all(
            //       const TextStyle(fontSize: 14, fontWeight: FontWeight.w500),
            //     )),
            items: const [
              BottomNavigationBarItem(
                  icon: Icon(Icons.home_outlined),
                  activeIcon: Icon(Icons.home),
                  label: 'Home'),
              BottomNavigationBarItem(
                icon: Icon(Icons.schedule),
                activeIcon: Icon(Icons.schedule),
                label: 'Appointment',
              ),
              BottomNavigationBarItem(
                  icon: Icon(Icons.add_shopping_cart),
                  activeIcon: Icon(Icons.add_shopping_cart),
                  label: 'Obat'),
              BottomNavigationBarItem(
                  icon: Icon(Icons.receipt),
                  activeIcon: Icon(Icons.receipt),
                  label: 'Resep'),
              BottomNavigationBarItem(
                  icon: Icon(Icons.payment_outlined),
                  activeIcon: Icon(Icons.payment),
                  label: 'Payment'),
              // BottomNavigationBarItem(
              //     icon: Icon(Icons.history_outlined),
              //     activeIcon: Icon(Icons.history),
              //     label: 'Riwayat'),
              BottomNavigationBarItem(
                  icon: Icon(Icons.account_circle_outlined),
                  activeIcon: Icon(Icons.account_circle),
                  label: 'Profile'),
            ]),
      );
}
