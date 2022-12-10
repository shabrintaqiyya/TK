import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';


class TitleForm extends StatelessWidget {
  final String text;
  final Color textColor;
  const TitleForm({
    Key? key,
    required this.text,
    this.textColor = const Color(0xFF1f8cce),
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: GoogleFonts.poppins(
        color: textColor,
        fontSize: 40,
        fontWeight: FontWeight.bold
      ),
        textAlign: TextAlign.center
      // TextStyle(
      //   color: textColor,
      //   fontSize: 40,
      //   fontWeight: FontWeight.bold
      // ),
    );
  }
}