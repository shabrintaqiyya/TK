import 'package:flutter/material.dart';
//import 'package:provider/provider.dart';
// import 'package:rumah_sehat_app/pages/auth_page.dart';
import 'package:rumahsehat_flutter/screens/home.dart';
import 'package:rumahsehat_flutter/screens/home.dart';
import 'package:rumahsehat_flutter/screens/login/login_screen.dart';
//import 'providers/auth.dart';

void main() {
  runApp(MyApp());
}

// class MyApp extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     // TODO: implement build
//     return  MultiProvider(providers: [
//       ChangeNotifierProvider(create: (ctx) => Authh(),),
//     ],
//       builder: (context, child) => Consumer<Authh>
//         (builder: (context, auth, child) => MaterialApp(
//         debugShowCheckedModeBanner: false,
//         home: auth.isAuth ? HomePage() : LoginScreen(),)
//       ),
//     );
//   }
// }

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}
class _MyAppState extends State<MyApp> {
  final ThemeData theme = ThemeData();

  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rumah Sehat',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.cyan,
        canvasColor: Colors.white,
        fontFamily: 'Raleway',
        textTheme: ThemeData.light().textTheme.copyWith(
            bodyText1: const TextStyle(
              color: Color.fromRGBO(20, 51, 51, 1),
            ),
            bodyText2: const TextStyle(
              color: Color.fromRGBO(20, 51, 51, 1),
            ),
            headline6: const TextStyle(
              fontSize: 20,
              fontFamily: 'RobotoCondensed',
              fontWeight: FontWeight.bold,
            )),
      ),
      initialRoute: '/',
      // routes: {
      //   '/': (ctx) => TabsScreen(),
      //   HomePageState.routeName: (ctx) => Homepage(),
      // },
      // ignore: missing_return
      onGenerateRoute: (settings) {
        print(settings.arguments);
      },
      onUnknownRoute: (settings) {
        return MaterialPageRoute(
          builder: (ctx) => LoginScreen(),
        );
      },
    );
  }
}
