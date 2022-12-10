import 'package:flutter/material.dart';


class Background extends StatelessWidget {
  final Widget child;
  const Background({
    Key? key,
    required this.child,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: double.infinity,
      width: double.infinity,
      decoration: BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
          colors: [
            Color(0x66F3F4F6),
            Color(0x99E5E7EB),
            Color(0xccE5E7EB),
            Color(0xffE5E7EB),
            Color(0xFF1f8cce)
          ]
        )
      ),
      child: SingleChildScrollView(
        physics: AlwaysScrollableScrollPhysics(),
        padding: EdgeInsets.symmetric(
          horizontal: 25,
          vertical: 60,
        ),
        child: child,
      )
    );    
  }
}