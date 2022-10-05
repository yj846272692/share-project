import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:share_app/model/User.dart';
import 'package:share_app/page/mine_page.dart';
import 'package:share_app/page/share_page.dart';
import 'package:share_app/util/cache.dart';
import 'package:share_app/widget/carousel.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  User? user;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  void initState() {
    Cache.init();
    // user = Cache.getObject('user');
  }

  final List<Widget> _pages = [
    const SharePage(),
    const Carousel(),
    const MinePage(),
  ];

  final _items = [
    BottomNavigationBarItem(label: 'home', icon: Icon(Icons.home)),
    BottomNavigationBarItem(label: 'share', icon: Icon(Icons.share)),
    BottomNavigationBarItem(label: 'mine', icon: Icon(Icons.person))
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_items.elementAt(_selectedIndex).label ?? ''),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: _items,
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
      ),
      body: _pages.elementAt(_selectedIndex),
    );
  }
}
